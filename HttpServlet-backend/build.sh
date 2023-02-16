#!/usr/bin/env bash

# Generate war file
mvn package

# Set up a Maven Wrapper
#mvn -N wrapper:wrapper

# Build the image from the Dockerfile and t(ag) it backend:latest
docker image build -t backend:latest .

# Run the container with port to localhost:5000 and name backend
docker run -it --rm -p 4000:8080 --name backend backend:latest
