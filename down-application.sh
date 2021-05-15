#!/bin/bash

docker stop register
docker rm register

pid=`pgrep -f register-1.0.0`
if [ -z "$pid" ]
then
    echo "System wasn't started"
else
    echo "Shutting down system"
    kill -9 "$pid"
    echo "Shutting down completed"
fi

