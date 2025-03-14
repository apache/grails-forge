#!/bin/bash

set -e

echo "Update grailsVersion to ${GITHUB_REF:11}"
sed -i "s/^grailsVersion.*$/grailsVersion\=${GITHUB_REF:11}/" gradle.properties
sed -i "s/grailsVersion=${GITHUB_REF:11}-SNAPSHOT/grailsVersion\=${GITHUB_REF:11}/" grails-forge-core/src/test/groovy/org/grails/forge/feature/grails/GrailsGradlePluginSpec.groovy
echo "value=${GITHUB_REF:11}" >> $GITHUB_OUTPUT
git add gradle.properties
git add grails-forge-core/src/test/groovy/org/grails/forge/feature/grails/GrailsGradlePluginSpec.groovy