#!/bin/bash
#
#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.  The ASF licenses this file
#  to you under the Apache License, Version 2.0 (the
#  "License"); you may not use this file except in compliance
#  with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing,
#  software distributed under the License is distributed on an
#  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#  KIND, either express or implied.  See the License for the
#  specific language governing permissions and limitations
#  under the License.
#
set -e

echo "Setting new Grails snapshot version"
sed -i "s/^grailsVersion.*$/grailsVersion\=${NEXT_VERSION}-SNAPSHOT/" gradle.properties
sed -i "s/grailsVersion=${GITHUB_REF:11}/grailsVersion\=${NEXT_VERSION}-SNAPSHOT/" grails-forge-core/src/test/groovy/org/grails/forge/feature/grails/GrailsGradlePluginSpec.groovy
git add gradle.properties
git add grails-forge-core/src/test/groovy/org/grails/forge/feature/grails/GrailsGradlePluginSpec.groovy