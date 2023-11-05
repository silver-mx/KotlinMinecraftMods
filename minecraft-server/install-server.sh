#!/usr/bin/bash

WORKING_DIR=$(cd "$(dirname "$0")" && pwd)

#Build server
"$WORKING_DIR"/scripts/build-server.sh "$WORKING_DIR"
# Download plugins
"$WORKING_DIR"/scripts/download-plugins.sh "$WORKING_DIR"
# Download mods
"$WORKING_DIR"/scripts/download-mods.sh "$WORKING_DIR"

