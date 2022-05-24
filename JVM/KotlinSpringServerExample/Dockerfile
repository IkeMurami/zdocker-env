FROM adoptopenjdk/openjdk11 AS builder

# move sources to container
ADD . /app
WORKDIR /app

# build sources to Jar
RUN sh gradlew clean
RUN sh gradlew bootJar

FROM adoptopenjdk/openjdk11

ENV START_SLEEP 0
ENV BASE_DOMAIN "http://localhost:3000"

# Get Jar from Builder container
COPY --from=builder /app/build/libs/MyService*.jar /app/my-service.jar

# ADD ./build/libs/MyService*.jar /app/my-service.jar
# Run app
CMD echo "The application will start in ${START_SLEEP}s..." && \
    sleep ${START_SLEEP} && \
    java -jar /app/my-service.jar
