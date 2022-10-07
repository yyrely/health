#!/bin/bash

docker stop health
docker rm health
docker rmi health

mvn clean package -Dmaven.test.skip=true
docker build -t health .
docker run -d -p 8060:8060 --name health health