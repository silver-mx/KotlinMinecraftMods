#! /usr/bin/bash

WORKING_DIR=$(cd $(dirname $0) && pwd)
PLUGINS_DIR=$WORKING_DIR/plugins

if [ ! -d "$PLUGINS_DIR" ]; then
  mkdir -p "$PLUGINS_DIR"
fi

curl -L https://download.geysermc.org/v2/projects/geyser/versions/latest/builds/latest/downloads/spigot --output $PLUGINS_DIR/Geyser-Spigot.jar

curl -L https://download.geysermc.org/v2/projects/floodgate/versions/latest/builds/latest/downloads/spigot --output $PLUGINS_DIR/floodgate-spigot.jar
