#!/bin/bash

BROWSER="remote:chrome"
TAGS="@Test and @UI"
NODES=3
ENV="ONE=one_value TWO=two_value one_more_three_value FOUR=#four_value #FIVE=five_value SIX"
TEAM=enigma

deploy() {
  docker network create "$TEAM-grid"
  docker run -d --name "$TEAM-selenium-hub" \
    --net "$TEAM-grid" --expose 4442-4444  \
    -e SE_SESSION_REQUEST_TIMEOUT=500 \
    -e SE_SESSION_RETRY_INTERVAL=2 \
    selenium/hub:4.15.0-20231122
  for i in $(seq 1 $NODES); do
    docker run -d --name "$TEAM-node-$1-$i" \
      --net "$TEAM-grid" --expose 7900 \
      --shm-size="2g" \
      -e SE_EVENT_BUS_HOST="$TEAM-selenium-hub" \
      -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
      -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
      selenium/node-$1:4.15.0-20231122;
  done
}

create_image() {
  docker build -t my_framework -f Jenkins/Dockerfiles/Dockerfile.test .
}

run_mvn() {
  mkdir results || true
  chmod 777 results
  mkdir downloads || true
  docker run -t --name my_framework --rm \
    --net "$TEAM-grid" \
    -e TAGS="$TAGS" \
    -e NODES="$NODES" \
    -e ENV="$ENV" \
    -e BROWSER="$BROWSER" \
    -e TEAM="$TEAM" \
    -v ./results:/var/src/app/results \
    -v ./downloads:/var/src/app/downloads \
    my_framework \
    mvn verify
#    --add-host=host.docker.internal:host-gateway
}

remove_container() {
  docker rm my_framework
  docker rmi my_framework
}

teardown() {
  docker rm $(docker stop "$TEAM-selenium-hub")
  for i in $(seq 1 $NODES); do
    docker rm $(docker stop "$TEAM-node-$1-$i");
  done
  docker network rm "$TEAM-grid"
}

deploy "chrome"
create_image
run_mvn
#remove_container
#teardown "chrome"

#echo $TAGS