#! /usr/bin/bash

WORKING_DIR=$(cd $(dirname $0) && pwd)

$WORKING_DIR/download-plugins.sh

java -Djava.awt.headless=true -Dcom.mojang.eula.agree=true -jar spigot-current.jar --nogui
