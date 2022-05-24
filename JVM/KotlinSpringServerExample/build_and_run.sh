#!/usr/bin/env bash

echo "Clean docker containers and images"
docker compose down --rmi all

echo "Build Spring app"
sh gradlew clean
sh gradlew bootJar

echo "Build and run container"

docker compose up