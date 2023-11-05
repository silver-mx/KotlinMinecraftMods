#!/usr/bin/bash

WORKING_DIR=$1
PLUGINS_DIR=$WORKING_DIR/plugins
HELLO_MINECRAFT_MOD="HelloMinecraft-1.0-20231104.202557-1-jar-with-dependencies.jar"
ABOUT_ME_MOD="AboutMePlugin-1.0-20231104.202557-1-jar-with-dependencies.jar"
MOBS_MOD="MobsPlugin-1.0-20231104.202557-1-jar-with-dependencies.jar"
OUR_WORLD_MOD="OurWorldPlugin-1.0-20231104.202557-1-jar-with-dependencies.jar"

if [ ! -d "$PLUGINS_DIR" ]; then
  mkdir -p "$PLUGINS_DIR"
fi

function downloadAndCheck() {
  FOLDER_NAME=$(echo "$1" | cut -d "-" -f 1)

  # Download JAR
  aws s3 cp s3://my-dev-maven-repo/snapshots/dns/mods/"$FOLDER_NAME"/1.0-SNAPSHOT/"$1" . --profile dev-server
  # Download SHA1
  aws s3 cp s3://my-dev-maven-repo/snapshots/dns/mods/"$FOLDER_NAME"/1.0-SNAPSHOT/"$1".sha1 . --profile dev-server

  EXPECTED_DIGEST=$(cat "$1".sha1)
  ACTUAL_DIGEST=$(sha1sum --binary "$1" | head -c 40)

  if [ "$EXPECTED_DIGEST" = "$ACTUAL_DIGEST" ]; then
      mv "$1" "$PLUGINS_DIR" || exit
      rm "$1".sha1 || exit
      echo "The file [$1] was downloaded and installed OK ..."
  else
      echo "The file [$1] is corrupted, exiting ..."
      exit 1
  fi
}

downloadAndCheck $HELLO_MINECRAFT_MOD
downloadAndCheck $ABOUT_ME_MOD
downloadAndCheck $MOBS_MOD
downloadAndCheck $OUR_WORLD_MOD
