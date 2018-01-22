#!/usr/bin/env bash

# Install ZooKeeper to maintain configuration information, provide distributed synchronization, naming and provide group services.
sudo apt-get install zookeeperd -y

# Check ZooKeeper
netstat -ant | grep :2181

# Download installation package
wget http://www-eu.apache.org/dist/kafka/1.0.0/kafka_2.11-1.0.0.tgz

# Create folder for installation
sudo mkdir /opt/Kafka

# Extract to download archive
sudo tar -xvf kafka_2.11-1.0.0.tgz -C /opt/Kafka/

# Start Kafka Server
# sudo  /opt/Kafka/kafka_2.11-1.0.0/bin/kafka-server-start.sh /opt/Kafka/kafka_2.10-0.10.0.1/config/server.properties