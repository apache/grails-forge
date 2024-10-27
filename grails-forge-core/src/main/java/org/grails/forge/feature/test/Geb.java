/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.forge.feature.test;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.grails.forge.application.ApplicationType;
import org.grails.forge.application.Project;
import org.grails.forge.application.generator.GeneratorContext;
import org.grails.forge.build.dependencies.Dependency;
import org.grails.forge.build.gradle.GradlePlugin;
import org.grails.forge.feature.*;
import org.grails.forge.feature.test.template.groovyJunit;
import org.grails.forge.feature.test.template.webdriverBinariesPlugin;
import org.grails.forge.options.*;
import org.grails.forge.template.RockerTemplate;
import org.grails.forge.template.RockerWritable;
import org.grails.forge.feature.test.template.gebConfig;

import java.util.Set;
import java.util.stream.Stream;

@Singleton
public class Geb implements DefaultFeature {

    private final Spock spock;

    public Geb(Spock spock) {
        this.spock = spock;
    }

    @Override
    public boolean shouldApply(ApplicationType applicationType, Options options, Set<Feature> selectedFeatures) {
        return applicationType == ApplicationType.WEB && options.getTestFramework() != TestFramework.JUNIT;
    }

    @NonNull
    @Override
    public String getName() {
        return "geb";
    }

    @Override
    public String getTitle() {
        return "Geb Functional Testing for Grails";
    }

    @NonNull
    @Override
    public String getDescription() {
        return "This plugins configure Geb for Grails framework to write automation tests.";
    }

    @Override
    public int getOrder() {
        return FeaturePhase.TEST.getOrder();
    }

    @Override
    public boolean supports(ApplicationType applicationType) {
        return applicationType == ApplicationType.WEB || applicationType == ApplicationType.WEB_PLUGIN;
    }

    @Override
    public String getDocumentation() {
        return "https://github.com/grails3-plugins/geb#readme";
    }

    @Override
    public String getThirdPartyDocumentation() {
        return "https://www.gebish.org/manual/current/";
    }

    @Override
    public void processSelectedFeatures(FeatureContext featureContext) {
        if (!featureContext.isPresent(Spock.class) && spock != null) {
            featureContext.addFeature(spock);
        }
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.addBuildPlugin(GradlePlugin.builder()
                .id("com.github.erdi.webdriver-binaries")
                .lookupArtifactId("webdriver-binaries-gradle-plugin")
                .extension(new RockerWritable(webdriverBinariesPlugin.template(generatorContext.getProject(), generatorContext.getOperatingSystem())))
                        .version("3.2")
                .build());

        generatorContext.addDependency(Dependency.builder()
                .groupId("org.grails.plugins")
                .artifactId("geb")
                .integrationTestImplementationTestFixtures());

        Stream.of("api", "support", "remote-driver")
                .map(name -> "selenium-" + name)
                .forEach(name -> generatorContext.addDependency(Dependency.builder()
                        .groupId("org.seleniumhq.selenium")
                        .artifactId(name)
                        .test()));

        generatorContext.addDependency(Dependency.builder()
                .groupId("org.seleniumhq.selenium")
                .artifactId("selenium-firefox-driver")
                .testRuntime());
        generatorContext.addDependency(Dependency.builder()
                .groupId("org.seleniumhq.selenium")
                .artifactId("selenium-safari-driver")
                .testRuntime());

        TestFramework testFramework = generatorContext.getTestFramework();
        String integrationTestSourcePath = generatorContext.getIntegrationTestSourcePath("/{packagePath}/{className}");
        Project project = generatorContext.getProject();
        TestRockerModelProvider provider = new DefaultTestRockerModelProvider(org.grails.forge.feature.test.template.spock.template(project),
                groovyJunit.template(project));
        generatorContext.addTemplate("applicationTest",
                new RockerTemplate(integrationTestSourcePath, provider.findModel(Language.DEFAULT_OPTION, testFramework))
        );
        generatorContext.addTemplate("gebConfig",
                new RockerTemplate("src/integration-test/resources/GebConfig.groovy", gebConfig.template(project)));
    }
}
