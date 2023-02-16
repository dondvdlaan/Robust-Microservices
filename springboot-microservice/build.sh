#!/usr/bin/env bash

# Set up a Maven Wrapper
mvn -N wrapper:wrapper

# Build the image from the Dockerfile and t(ag) it springboot-microservice:latest
docker image build -t springboot-microservice:latest .

# Run the container with port to localhost:5000 and name springboot-microservice
docker run -it --rm -p 5000:8080 --name springboot-microservice springboot-microservice:latest
