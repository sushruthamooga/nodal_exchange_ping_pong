#!/bin/bash

# Wait for RabbitMQ to start up and be responsive

# Declare queues
wget -qO- --user=guest --password=guest --post-data '{"durable": true}' http://localhost:15672/api/queues/%2F/service1-publish-queue

wget -qO- --user=guest --password=guest --post-data '{"durable": true}' http://localhost:15672/api/queues/%2F/service2-publish-queue


# Declare exchanges
wget -qO- --user=guest --password=guest --post-data '{"type": "direct", "durable": true}' http://localhost:15672/api/exchanges/%2F/service1-exchange

wget -qO- --user=guest --password=guest --post-data '{"type": "direct", "durable": true}' http://localhost:15672/api/exchanges/%2F/service2-exchange


# Bind queues to exchanges
wget -qO- --user=guest --password=guest --post-data '{"routing_key": "key-1", "queue": "service1-publish-queue"}' http://localhost:15672/api/bindings/%2F/e/service1-exchange/q/service1-publish-queue

wget -qO- --user=guest --password=guest --post-data '{"routing_key": "key-2", "queue": "service2-publish-queue"}' http://localhost:15672/api/bindings/%2F/e/service2-exchange/q/service2-publish-queue
