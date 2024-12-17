#!/bin/bash
app_version=$(ls -t target | grep Hermes | head -n1)
echo "$app_version"
mvn clean install
docker build --no-cache --build-arg JAR_FILE_ARG="$app_version" -t weedesigners/hermes:latest .