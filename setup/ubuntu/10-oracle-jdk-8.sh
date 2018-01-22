#!/usr/bin/env bash

# Update Server
sudo apt-get update -y
sudo apt-get upgrade -y

# Add a Personal Package Archives (PPA)
sudo add-apt-repository -y ppa:webupd8team/java

# Update the package database
sudo apt-get update -y

# Install the Oracle JRE
sudo apt-get install oracle-java8-set-default -y

# Print Java Version
sudo java -version