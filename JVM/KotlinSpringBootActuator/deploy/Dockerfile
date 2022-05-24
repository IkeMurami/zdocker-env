FROM adoptopenjdk/openjdk8 AS builder

RUN apt update && apt install -y maven

# move sources to container
COPY . /app
WORKDIR /app

# build sources to Jar

RUN mvn install

FROM adoptopenjdk/openjdk8 AS starter

COPY --from=builder /app/target/MyService-0.0.1-SNAPSHOT.jar /app/my-service.jar

CMD java -jar /app/my-service.jar