#!/bin/bash

# Check if the version argument is provided
if [ "$#" -ne 1 ]; then
  echo "Usage: $0 <new_version>"
  exit 1
fi

# Assign the new version from the argument
NEW_VERSION="$1"

# Define the path to the category.xml file
CATEGORY_XML="./com.developer.nefarious.zjoule.updatesite/category.xml"

# Check if the category.xml file exists
if [ ! -f "$CATEGORY_XML" ]; then
  echo "Error: $CATEGORY_XML file not found!"
  exit 1
fi

# Update the url and version attributes inside the feature tag
sed -i -E "s|(<feature url=\"features/com.developer.nefarious.zjoule_)[^\"]*(\\.jar\" id=\"com.developer.nefarious.zjoule\" version=\")[^\"]*\"|\\1${NEW_VERSION}\\2${NEW_VERSION}\"|" "$CATEGORY_XML"

# Provide feedback to the user
if [ $? -eq 0 ]; then
  echo "Successfully updated version to ${NEW_VERSION} in $CATEGORY_XML"
else
  echo "Failed to update $CATEGORY_XML"
  exit 1
fi
