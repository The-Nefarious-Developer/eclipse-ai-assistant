#!/bin/bash

# LATEST_VERSION=$1
LATEST_VERSION=v2.0.0

# This file is being executed at the website-repo

#----------------------------------------

# Create a new version variable with the dots replaced by underscores
NEW_FILE_NAME=$(echo $LATEST_VERSION | tr . _)

# Copy the docs/template.md and name it after the new version
cp docs/template.md docs/$NEW_VERSION.md

# Replace the placeholder __NEW_VERSION__ with the latest version in the new file
sed -i "s/__NEW_VERSION__/$LATEST_VERSION/g" docs/$NEW_FILE_NAME.md

# Add the new version to the mkdocs.yml file
sed -i "/- Versions:/a\      - $LATEST_VERSION: $NEW_FILE_NAME.md" mkdocs.yml

#----------------------------------------

# Create a directory for the version in the /docs folder
mkdir -p docs/$LATEST_VERSION

# Download the latest version of the documentation
wget -O docs/$LATEST_VERSION/doc.zip https://github.com/The-Nefarious-Developer/zjoule/releases/download/$LATEST_VERSION/doc.zip
unzip -o docs/$LATEST_VERSION/doc.zip -d docs/$LATEST_VERSION/doc && rm docs/$LATEST_VERSION/doc.zip

# Download the latest version of the plugin
wget -O docs/$LATEST_VERSION/plugin.zip https://github.com/The-Nefarious-Developer/zjoule/releases/download/$LATEST_VERSION/plugin.zip
unzip -o docs/$LATEST_VERSION/plugin.zip -d docs/$LATEST_VERSION/plugin && rm docs/$LATEST_VERSION/plugin.zip