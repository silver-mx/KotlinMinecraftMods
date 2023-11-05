#!/usr/bin/bash

WORKING_DIR=$(cd "$(dirname "$0")" && pwd)

cd "$WORKING_DIR" || exit

java -Djava.awt.headless=true -Dcom.mojang.eula.agree=true -jar spigot-current.jar --nogui
