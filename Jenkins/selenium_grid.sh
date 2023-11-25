#!/bin/bash

deploy() {
  docker network create grid
  docker run -d --net grid -p 4442-4444:4442-4444 --name selenium-hub selenium/hub:4.15.0-20231122
  docker run -d --net grid -p 5900:5900 --name node-$1 \
      --shm-size="2g" \
      -e SE_EVENT_BUS_HOST=selenium-hub \
      -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
      -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
      selenium/node-$1:4.15.0-20231122
}

teardown() {
  docker stop selenium-hub && docker rm $_
  docker stop node-$1 && docker rm $_
  docker network rm grid
}

if [[ $1 == "deploy" ]]; then
  deploy $2
else
  teardown $2
fi