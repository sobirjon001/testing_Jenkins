#!/bin/bash
#wait-for-grid.sh team_name

set -e

cmd="$@"

url="http://$TEAM-selenium-hub:4444/wd/hub/status"
wait_interval_in_seconds=1
max_wait_time_in_seconds=30
end_time=$((SECONDS + max_wait_time_in_seconds))
time_left=$max_wait_time_in_seconds

echo "url is $url"

while [ $SECONDS -lt $end_time ]; do
  response=$(curl -sL "$url" | jq -r '.value.ready')
  if [ -n "$response" ] && [ "$response" ]; then
    echo "Selenium Grid for team $TEAM is up - executing tests"
    break
  else
    echo "Waiting for Grid for team $1. Sleeping fot $wait_interval_in_seconds second(s). $time_left seconds seconds left until timeout."
    sleep $wait_interval_in_seconds
    time_left=$((time_left - wait_interval_in_seconds))
  fi
done

if [ $SECONDS -ge $end_time ]; then
  echo "Timeout: The Grid for team $TEAM was not started within $max_wait_time_in_seconds seconds."
  exit 1
else
  exec $cmd
fi