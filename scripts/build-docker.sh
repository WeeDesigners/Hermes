#!/bin/bash
mvn clean install
app_version=$(ls -t target | grep Hermes | head -n1)
echo "$app_version"
sudo docker build --no-cache --build-arg JAR_FILE_ARG="$app_version" -t weedesigners/hermes:latest .
