FROM ubuntu:16.04 AS builder

RUN apt-get update && apt-get install -y openjdk-8-jdk git make wget unzip

# ANT: https://ant.apache.org/bindownload.cgi
ARG ANT_VERSION=1.9.16
RUN wget https://dlcdn.apache.org//ant/binaries/apache-ant-${ANT_VERSION}-bin.zip
RUN unzip apache-ant-${ANT_VERSION}-bin.zip
ENV PATH=/apache-ant-${ANT_VERSION}/bin:$PATH
ENV ANT_OPTS=-Dfile.encoding=UTF-8


# GWT SDK: https://www.gwtproject.org/download.html
ARG GWT_SDK_VERSION=2.9.0
RUN wget https://storage.googleapis.com/gwt-releases/gwt-${GWT_SDK_VERSION}.zip
RUN unzip gwt-${GWT_SDK_VERSION}.zip
ENV PATH=/gwt-${GWT_SDK_VERSION}:$PATH


# Copy source files
COPY ./src /source

# Check /source folder: need create a new app or we have an app on external volume 
WORKDIR /tools
COPY isempty.sh .

WORKDIR /source
RUN sh /tools/isempty.sh

# Build the app
RUN ant build
# Generate a .war package ready to deploy
RUN ant war


FROM jetty AS starter

COPY --from=builder /source /source
COPY --from=builder /source/*.war /var/lib/jetty/webapps/ROOT.war