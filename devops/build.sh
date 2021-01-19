# ----- IMPORTANT ------
# This script assumes that it will be invoked from the devops directory.
# The docker images are pushed to my local docker hub

# Build the project from the root directory
cd .. && mvn clean package && cd ./devops

# Build the docker image
docker build -t kasper360/randoli -f ./Dockerfile ..

# Push the image to docker hub
docker push kasper360/randoli:latest