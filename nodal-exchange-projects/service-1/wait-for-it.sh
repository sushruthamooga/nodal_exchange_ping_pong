#!/bin/bash
# wait-for-it.sh

host=$1
port=$2
shift 2

until nc -z -v -w30 $host $port
do
  echo "Waiting for $host:$port to be available..."
  sleep 2
done

echo "$host:$port is available!"
exec "$@"
