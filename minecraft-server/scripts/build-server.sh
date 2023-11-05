#!/usr/bin/bash

WORKING_DIR=$1
BUILD_TOOL="BuildTools.jar"
TMP_FOLDER="$WORKING_DIR"/build-tmp

function exitIfError() {
    if [ "$?" -ne 0 ]; then
        exit 1
    fi
}

echo "Working dir $WORKING_DIR"

# First delete the tmp folder if it exists
test "$TMP_FOLDER" && rm -rf "$TMP_FOLDER"

# Create it again
mkdir "$TMP_FOLDER" && cd "$TMP_FOLDER" || exit
test "$TMP_FOLDER"

exitIfError

# Download Spigot's build tool
curl https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/$BUILD_TOOL --output $BUILD_TOOL

# Build Spigot's latest version
java -jar $BUILD_TOOL --rev latest

exitIfError

# Copy built artifact to parent folder and create a simlink to current version
JAR_FILE="$(ls spigot-*)"
SYMLINK_FILE=$WORKING_DIR/spigot-current.jar
cp "$JAR_FILE" "$WORKING_DIR"
rm "$SYMLINK_FILE"
ln -s "$WORKING_DIR"/"$JAR_FILE" "$SYMLINK_FILE"

exitIfError

# Clean up the tmp folder
cd "$WORKING_DIR" || exit
rm -rf "$TMP_FOLDER"

