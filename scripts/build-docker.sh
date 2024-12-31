#!/bin/bash
if [[ $# != 1 ]]; then
    echo "Eror: Please provide a tag name for the image"
    exit 1
fi
tag_name=$1
mvn clean install || exit 1
app_version=$(ls -t target | grep Hermes | head -n1)
echo "App version: $app_version, image tag: $tag_name"
sudo docker build --no-cache --build-arg JAR_FILE_ARG="$app_version" -t weedesigners/hermes:${tag_name} . || exit 1
