# Oracle JDK Builder
# ------------------
#   Base on https://github.com/oracle/docker-images/blob/main/OracleJava/11/Dockerfile
#   Oracle Linux Docker Image: https://hub.docker.com/_/oraclelinux
FROM oraclelinux:8 as oraclejdk_builder

RUN set -eux; dnf install -y tar

# Default to UTF-8 file.encoding
ENV LANG en_US.UTF-8


# Environment variables for the builder image.
# Required to validate that you are using the correct file
ENV JAVA_VERSION=18
ENV JAVA_URL=https://download.oracle.com/java/${JAVA_VERSION}/latest
ENV JAVA_HOME=/usr/java/jdk-${JAVA_VERSION}


RUN set -eux; \
	ARCH=$(uname -m) && \
	# Java uses just x64 in the name of the tarball
    if [ "$ARCH" == "x86_64" ]; \
        then ARCH="x64"; \
    fi && \
    JAVA_PKG="$JAVA_URL/jdk-${JAVA_VERSION}_linux-${ARCH}_bin.tar.gz" ; \
	JAVA_SHA256=$(curl "$JAVA_PKG".sha256) ; \ 
	curl --output /tmp/jdk.tgz "$JAVA_PKG" && \
	echo "$JAVA_SHA256 */tmp/jdk.tgz" | sha256sum -c; \
	mkdir -p "$JAVA_HOME"; \
	tar --extract --file /tmp/jdk.tgz --directory "$JAVA_HOME" --strip-components 1


# Oracle JDK Container
# --------------------
FROM oraclelinux:8 as oraclejdk

# Default to UTF-8 file.encoding
ENV LANG en_US.UTF-8

ENV JAVA_VERSION=18
ENV	JAVA_HOME=/usr/java/jdk-${JAVA_VERSION}
ENV	PATH=$JAVA_HOME/bin:$PATH

# Copy the uncompressed Java Runtime from the builder image
COPY --from=oraclejdk_builder $JAVA_HOME $JAVA_HOME

RUN set -eux; \
# Ensure we get the latest OL 8 updates available at build time
	dnf -y update; \
# JDK assumes freetype is available	
	dnf install -y \
		freetype fontconfig \
	; \
	rm -rf /var/cache/dnf; \
	ln -sfT "$JAVA_HOME" /usr/java/default; \
	ln -sfT "$JAVA_HOME" /usr/java/latest; \
	for bin in "$JAVA_HOME/bin/"*; do \
		base="$(basename "$bin")"; \
		[ ! -e "/usr/bin/$base" ]; \
		alternatives --install "/usr/bin/$base" "$base" "$bin" 20000; \
	done;


# Oracle WebLogic Builder
# =======================
#   Base on: https://github.com/oracle/docker-images/blob/main/OracleWebLogic/dockerfiles/14.1.1.0/Dockerfile.generic-11
FROM oraclejdk AS weblogic_builder

# Common environment variables required for this build (do NOT change)
# --------------------------------------------------------------------
ENV USER_HOME=/u01
ENV USER_NAME=oracle
ENV ORACLE_HOME=${USER_HOME}/oracle
ENV USER_MEM_ARGS="-Djava.security.egd=file:/dev/./urandom"
ENV PATH=$PATH:${JAVA_HOME}/bin:${ORACLE_HOME}/oracle_common/common/bin:${ORACLE_HOME}/wlserver/common/bin

# Setup filesystem and oracle user
# Adjust file permissions, go to /u01 as user 'oracle' to proceed with WLS installation
# ------------------------------------------------------------
RUN mkdir ${USER_HOME} && \
    useradd -b ${USER_HOME} -d ${ORACLE_HOME} -m -s /bin/bash ${USER_NAME} && \
    chown ${USER_NAME}:root -R ${USER_HOME} && \
    chmod -R 775 ${USER_HOME}

ENV ORACLE_WEBLOGIC_SERVER_VERSION=14.1.1.0.0
ENV FMW_PKG=fmw_${ORACLE_WEBLOGIC_SERVER_VERSION}_wls_lite_Disk1_1of1.zip
ENV FMW_JAR=fmw_${ORACLE_WEBLOGIC_SERVER_VERSION}_wls_lite_generic.jar

# Copy packages
# -------------
COPY --chown=oracle:root WebLogicFMW/${FMW_PKG} WebLogicFMW/install.file WebLogicFMW/oraInst.loc ${USER_HOME}

# Install
# -------
USER ${USER_NAME}
WORKDIR ${USER_HOME}

# Install from https://www.oracle.com/middleware/technologies/weblogic-server-downloads.html
RUN cd ${USER_HOME} && ${JAVA_HOME}/bin/jar xf ${USER_HOME}/${FMW_PKG} && cd - && \
    ${JAVA_HOME}/bin/java -jar ${USER_HOME}/$FMW_JAR -silent -responseFile ${USER_HOME}/install.file -invPtrLoc ${USER_HOME}/oraInst.loc -jreLoc $JAVA_HOME -ignoreSysPrereqs -force -novalidation ORACLE_HOME=$ORACLE_HOME INSTALL_TYPE="WebLogic Server" && \
    rm ${USER_HOME}/${FMW_JAR} ${USER_HOME}/${FMW_PKG} ${USER_HOME}/install.file && \
    rm -rf ${ORACLE_HOME}/cfgtoollogs



# Final image stage
# =================
FROM oraclejdk AS weblogic

ENV USER_HOME=/u01
ENV USER_NAME=oracle
ENV ORACLE_HOME=${USER_HOME}/oracle
ENV USER_MEM_ARGS="-Djava.security.egd=file:/dev/./urandom"
ENV SCRIPT_FILE=${ORACLE_HOME}/createAndStartEmptyDomain.sh
ENV HEALTH_SCRIPT_FILE=${ORACLE_HOME}/get_healthcheck_url.sh
ENV PATH=$PATH:${JAVA_HOME}/bin:${ORACLE_HOME}/oracle_common/common/bin:${ORACLE_HOME}/wlserver/common/bin

# Domain and Server environment variables
# ------------------------------------------------------------
ENV DOMAIN_NAME="${DOMAIN_NAME:-base_domain}" \
    ADMIN_LISTEN_PORT="${ADMIN_LISTEN_PORT:-7001}" \
    ADMIN_NAME="${ADMIN_NAME:-AdminServer}" \
    ADMINISTRATION_PORT_ENABLED="${ADMINISTRATION_PORT_ENABLED:-true}" \
    ADMINISTRATION_PORT="${ADMINISTRATION_PORT:-9002}"

# Setup filesystem and oracle user
# Adjust file permissions, go to /u01 as user 'oracle' to proceed with WLS installation
# ------------------------------------------------------------
RUN mkdir -p ${USER_HOME} && \
    chmod 775 ${USER_HOME} && \
    useradd -b ${USER_HOME} -d ${ORACLE_HOME} -m -s /bin/bash ${USER_NAME} && \
    chown ${USER_NAME}:root ${USER_HOME}

COPY --from=weblogic_builder --chown=${USER_NAME}:root ${USER_HOME} ${USER_HOME}


# Copy scripts
# ------------
COPY container-scripts/createAndStartEmptyDomain.sh container-scripts/create-wls-domain.py container-scripts/get_healthcheck_url.sh ${ORACLE_HOME}

RUN chmod +xr $SCRIPT_FILE $HEALTH_SCRIPT_FILE && \
    chown ${USER_NAME}:root $SCRIPT_FILE ${ORACLE_HOME}/create-wls-domain.py $HEALTH_SCRIPT_FILE


USER ${USER_NAME}

HEALTHCHECK --start-period=10s --timeout=30s --retries=3 CMD curl -k -s --fail `$HEALTH_SCRIPT_FILE` || exit 1
WORKDIR ${ORACLE_HOME}


# Define default command to start script.
CMD ["/u01/oracle/createAndStartEmptyDomain.sh"]