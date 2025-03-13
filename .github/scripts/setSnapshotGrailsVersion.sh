#!/bin/bash

set -e

echo "Setting new Grails snapshot version"
sed -i "s/^grailsVersion.*$/grailsVersion\=${NEXT_VERSION}-SNAPSHOT/" gradle.properties
sed -i "s/grailsVersion=${GITHUB_REF:11}/grailsVersion\=${NEXT_VERSION}-SNAPSHOT/" grails-forge-core/src/test/groovy/org/grails/forge/feature/grails/GrailsGradlePluginSpec.groovy
git add gradle.properties
git add grails-forge-core/src/test/groovy/org/grails/forge/feature/grails/GrailsGradlePluginSpec.groovy