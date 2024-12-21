#!/bin/bash

# Navigate to the parent folder of the script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PARENT_DIR="$(dirname "$SCRIPT_DIR")"
cd "$PARENT_DIR" || { echo "Error: Failed to navigate to parent folder."; exit 1; }

# Update the root directory pom and its reference on the test import
mvn versions:set -DnewVersion=2.0.0 -DprocessAllModules -DgenerateBackupPoms=false

# Update the pom and the manifest of the plugin module
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=2.0.0 -pl com.developer.nefarious.zjoule.plugin

# Update the pom and the manifest of the test module
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=2.0.0 -pl com.developer.nefarious.zjoule.test

# Update the pom and the feature file of the feature module
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=2.0.0 -pl com.developer.nefarious.zjoule.feature

# Update the pom of the updatesite module
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=2.0.0 -pl com.developer.nefarious.zjoule.updatesite

# Update the category.xml file with the new version at the updatesite module
chmod +x ./scripts/update-category.sh
./scripts/update-category.sh 2.0.0