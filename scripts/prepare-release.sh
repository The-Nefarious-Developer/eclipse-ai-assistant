#!/bin/bash

# Check if the version argument is provided
if [ "$#" -ne 1 ]; then
  echo "Usage: $0 <new_version>"
  exit 1
fi

# Assign the new version from the argument
NEW_VERSION=`echo $1 | cut -d'v' -f2` # Version should come from semantic release with a 'v' prefix

# Navigate to the parent folder of the script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PARENT_DIR="$(dirname "$SCRIPT_DIR")"
cd "$PARENT_DIR" || { echo "Error: Failed to navigate to parent folder."; exit 1; }

# # Update the root directory pom and its reference on the test import
./mvnw org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=${NEW_VERSION} -DprocessAllModules

# Update the pom and the manifest of the plugin module
./mvnw org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=${NEW_VERSION} -pl com.developer.nefarious.zjoule.plugin -am

# Update the pom and the manifest of the test module
./mvnw org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=${NEW_VERSION} -pl com.developer.nefarious.zjoule.test -am
./mvnw versions:use-dep-version -Dincludes=com.developer.nefarious.zjoule:com.developer.nefarious.zjoule.plugin -DdepVersion=${NEW_VERSION} -DforceVersion=true -DgenerateBackupPoms=false -pl com.developer.nefarious.zjoule.test

# Update the pom and the feature file of the feature module
./mvnw org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=${NEW_VERSION} -pl com.developer.nefarious.zjoule.feature -am

# Update the pom of the updatesite module
./mvnw org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=${NEW_VERSION} -pl com.developer.nefarious.zjoule.updatesite -am

# Update the category.xml file with the new version at the updatesite module
chmod +x ./scripts/update-category.sh
./scripts/update-category.sh ${NEW_VERSION}

# Generate target files for the new release
./mvnw clean package

# Create a release folder for semantic release
mkdir -p release

# Zip the updatesite and copy it to the release folder
PLUGIN_FILE_NAME="zjoule-${NEW_VERSION}"
(cd com.developer.nefarious.zjoule.updatesite/target/repository && zip -r "../../../release/${PLUGIN_FILE_NAME}.zip" .)

# Generate JavaDoc
./mvnw javadoc:javadoc -pl com.developer.nefarious.zjoule.plugin

# Zip the JavaDoc and copy it to the release folder
DOC_FILE_NAME="zjoule-javadoc-${NEW_VERSION}"
(cd com.developer.nefarious.zjoule.plugin/target/reports/apidocs && zip -r "../../../../release/${DOC_FILE_NAME}.zip" .)