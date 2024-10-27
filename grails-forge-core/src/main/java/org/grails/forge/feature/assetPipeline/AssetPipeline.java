/*
 * Copyright 2017-2020 original authors
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
package org.grails.forge.feature.assetPipeline;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.grails.forge.application.ApplicationType;
import org.grails.forge.application.generator.GeneratorContext;
import org.grails.forge.build.dependencies.CoordinateResolver;
import org.grails.forge.build.dependencies.Dependency;
import org.grails.forge.build.gradle.GradlePlugin;
import org.grails.forge.feature.Category;
import org.grails.forge.feature.DefaultFeature;
import org.grails.forge.feature.Feature;
import org.grails.forge.feature.assetPipeline.templates.assetPipelineExtension;
import org.grails.forge.options.Options;
import org.grails.forge.template.RockerWritable;
import org.grails.forge.template.URLTemplate;

import java.util.Set;

@Singleton
public class AssetPipeline implements DefaultFeature {

    private final CoordinateResolver coordinateResolver;

    public AssetPipeline(CoordinateResolver coordinateResolver) {
        this.coordinateResolver = coordinateResolver;
    }

    @NonNull
    @Override
    public String getName() {
        return "asset-pipeline-grails";
    }

    @Override
    public String getTitle() {
        return "Asset Pipeline Core";
    }

    @NonNull
    @Override
    public String getDescription() {
        return "The Asset-Pipeline is a plugin used for managing and processing static assets in JVM applications primarily via Gradle (however not mandatory). Read more at https://github.com/bertramdev/asset-pipeline";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.addBuildPlugin(GradlePlugin.builder()
                .id("com.bertramlabs.asset-pipeline")
                .extension(new RockerWritable(assetPipelineExtension.template(generatorContext.getApplicationType())))
                .build());

        generatorContext.addDependency(Dependency.builder()
                .groupId("com.bertramlabs.plugins")
                .artifactId("asset-pipeline-grails")
                .runtime());

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        generatorContext.addTemplate("advancedgrails_svg", new URLTemplate("grails-app/assets/images/advancedgrails.svg", classLoader.getResource("assets/images/advancedgrails.svg")));
        generatorContext.addTemplate("apple-touch-icon_png", new URLTemplate("grails-app/assets/images/apple-touch-icon.png", classLoader.getResource("assets/images/apple-touch-icon.png")));
        generatorContext.addTemplate("apple-touch-icon-retina_png", new URLTemplate("grails-app/assets/images/apple-touch-icon-retina.png", classLoader.getResource("assets/images/apple-touch-icon-retina.png")));
        generatorContext.addTemplate("documentation_svg", new URLTemplate("grails-app/assets/images/documentation.svg", classLoader.getResource("assets/images/documentation.svg")));
        generatorContext.addTemplate("favicon_ico", new URLTemplate("grails-app/assets/images/favicon.ico", classLoader.getResource("assets/images/favicon.ico")));
        generatorContext.addTemplate("grails_svg", new URLTemplate("grails-app/assets/images/grails.svg", classLoader.getResource("assets/images/grails.svg")));
        generatorContext.addTemplate("grails-cupsonly-logo-white_svg", new URLTemplate("grails-app/assets/images/grails-cupsonly-logo-white.svg", classLoader.getResource("assets/images/grails-cupsonly-logo-white.svg")));
        generatorContext.addTemplate("slack_svg", new URLTemplate("grails-app/assets/images/slack.svg", classLoader.getResource("assets/images/slack.svg")));
        generatorContext.addTemplate("spinner_gif", new URLTemplate("grails-app/assets/images/spinner.gif", classLoader.getResource("assets/images/spinner.gif")));
        generatorContext.addTemplate("database-add_png", new URLTemplate("grails-app/assets/images/skin/database_add.png", classLoader.getResource("assets/images/skin/database_add.png")));
        generatorContext.addTemplate("database-delete_png", new URLTemplate("grails-app/assets/images/skin/database_delete.png", classLoader.getResource("assets/images/skin/database_delete.png")));
        generatorContext.addTemplate("database-edit_png", new URLTemplate("grails-app/assets/images/skin/database_edit.png", classLoader.getResource("assets/images/skin/database_edit.png")));
        generatorContext.addTemplate("database-save_png", new URLTemplate("grails-app/assets/images/skin/database_save.png", classLoader.getResource("assets/images/skin/database_save.png")));
        generatorContext.addTemplate("database-table_png", new URLTemplate("grails-app/assets/images/skin/database_table.png", classLoader.getResource("assets/images/skin/database_table.png")));
        generatorContext.addTemplate("exclamation_png", new URLTemplate("grails-app/assets/images/skin/exclamation.png", classLoader.getResource("assets/images/skin/exclamation.png")));
        generatorContext.addTemplate("house_png", new URLTemplate("grails-app/assets/images/skin/house.png", classLoader.getResource("assets/images/skin/house.png")));
        generatorContext.addTemplate("information_png", new URLTemplate("grails-app/assets/images/skin/information.png", classLoader.getResource("assets/images/skin/information.png")));
        generatorContext.addTemplate("sorted_asc_gif", new URLTemplate("grails-app/assets/images/skin/sorted_asc.gif", classLoader.getResource("assets/images/skin/sorted_asc.gif")));
        generatorContext.addTemplate("sorted_desc_gif", new URLTemplate("grails-app/assets/images/skin/sorted_desc.gif", classLoader.getResource("assets/images/skin/sorted_desc.gif")));

        generatorContext.addTemplate("application_js", new URLTemplate("grails-app/assets/javascripts/application.js", classLoader.getResource("assets/javascripts/application.js")));
        generatorContext.addTemplate("bootstrap_bundle_js", new URLTemplate("grails-app/assets/javascripts/bootstrap.bundle.js", classLoader.getResource("assets/javascripts/bootstrap.bundle.js")));
        generatorContext.addTemplate("bootstrap_bundle_js_map", new URLTemplate("grails-app/assets/javascripts/bootstrap.bundle.js.map", classLoader.getResource("assets/javascripts/bootstrap.bundle.js.map")));
        generatorContext.addTemplate("bootstrap_bundle_min_js", new URLTemplate("grails-app/assets/javascripts/bootstrap.bundle.min.js", classLoader.getResource("assets/javascripts/bootstrap.bundle.min.js")));
        generatorContext.addTemplate("bootstrap_bundle_min_js_map", new URLTemplate("grails-app/assets/javascripts/bootstrap.bundle.min.js.map", classLoader.getResource("assets/javascripts/bootstrap.bundle.min.js.map")));
        generatorContext.addTemplate("bootstrap_js", new URLTemplate("grails-app/assets/javascripts/bootstrap.js", classLoader.getResource("assets/javascripts/bootstrap.js")));
        generatorContext.addTemplate("bootstrap_js_map", new URLTemplate("grails-app/assets/javascripts/bootstrap.js.map", classLoader.getResource("assets/javascripts/bootstrap.js.map")));
        generatorContext.addTemplate("bootstrap_min_js", new URLTemplate("grails-app/assets/javascripts/bootstrap.min.js", classLoader.getResource("assets/javascripts/bootstrap.min.js")));
        generatorContext.addTemplate("bootstrap_min_js_map", new URLTemplate("grails-app/assets/javascripts/bootstrap.min.js.map", classLoader.getResource("assets/javascripts/bootstrap.min.js.map")));
        generatorContext.addTemplate("jquery-3_5_1_js", new URLTemplate("grails-app/assets/javascripts/jquery-3.5.1.js", classLoader.getResource("assets/javascripts/jquery-3.5.1.js")));
        generatorContext.addTemplate("jquery-3_5_1_min_js", new URLTemplate("grails-app/assets/javascripts/jquery-3.5.1.min.js", classLoader.getResource("assets/javascripts/jquery-3.5.1.min.js")));
        generatorContext.addTemplate("jquery-3_5_1_min_js_map", new URLTemplate("grails-app/assets/javascripts/jquery-3.5.1.min.js.map", classLoader.getResource("assets/javascripts/jquery-3.5.1.min.js.map")));
        generatorContext.addTemplate("popper_js", new URLTemplate("grails-app/assets/javascripts/popper.js", classLoader.getResource("assets/javascripts/popper.js")));
        generatorContext.addTemplate("popper_min_js", new URLTemplate("grails-app/assets/javascripts/popper.min.js", classLoader.getResource("assets/javascripts/popper.min.js")));
        generatorContext.addTemplate("popper_min_js_map", new URLTemplate("grails-app/assets/javascripts/popper.min.js.map", classLoader.getResource("assets/javascripts/popper.min.js.map")));

        generatorContext.addTemplate("application_css", new URLTemplate("grails-app/assets/stylesheets/application.css", classLoader.getResource("assets/stylesheets/application.css")));
        generatorContext.addTemplate("bootstrap_css", new URLTemplate("grails-app/assets/stylesheets/bootstrap.css", classLoader.getResource("assets/stylesheets/bootstrap.css")));
        generatorContext.addTemplate("bootstrap_css_map", new URLTemplate("grails-app/assets/stylesheets/bootstrap.css.map", classLoader.getResource("assets/stylesheets/bootstrap.css.map")));
        generatorContext.addTemplate("bootstrap_min_css", new URLTemplate("grails-app/assets/stylesheets/bootstrap.min.css", classLoader.getResource("assets/stylesheets/bootstrap.min.css")));
        generatorContext.addTemplate("bootstrap_min_css_map", new URLTemplate("grails-app/assets/stylesheets/bootstrap.min.css.map", classLoader.getResource("assets/stylesheets/bootstrap.min.css.map")));
        generatorContext.addTemplate("bootstrap-grid_css", new URLTemplate("grails-app/assets/stylesheets/bootstrap-grid.css", classLoader.getResource("assets/stylesheets/bootstrap-grid.css")));
        generatorContext.addTemplate("bootstrap-grid_css_map", new URLTemplate("grails-app/assets/stylesheets/bootstrap-grid.css.map", classLoader.getResource("assets/stylesheets/bootstrap-grid.css.map")));
        generatorContext.addTemplate("bootstrap-grid_min_css", new URLTemplate("grails-app/assets/stylesheets/bootstrap-grid.min.css", classLoader.getResource("assets/stylesheets/bootstrap-grid.min.css")));
        generatorContext.addTemplate("bootstrap-grid_min_css_map", new URLTemplate("grails-app/assets/stylesheets/bootstrap-grid.min.css.map", classLoader.getResource("assets/stylesheets/bootstrap-grid.min.css.map")));
        generatorContext.addTemplate("bootstrap-reboot_css", new URLTemplate("grails-app/assets/stylesheets/bootstrap-reboot.css", classLoader.getResource("assets/stylesheets/bootstrap-reboot.css")));
        generatorContext.addTemplate("errors_css", new URLTemplate("grails-app/assets/stylesheets/errors.css", classLoader.getResource("assets/stylesheets/errors.css")));
        generatorContext.addTemplate("grails_css", new URLTemplate("grails-app/assets/stylesheets/grails.css", classLoader.getResource("assets/stylesheets/grails.css")));
        generatorContext.addTemplate("main_css", new URLTemplate("grails-app/assets/stylesheets/main.css", classLoader.getResource("assets/stylesheets/main.css")));
        generatorContext.addTemplate("mobile_css", new URLTemplate("grails-app/assets/stylesheets/mobile.css", classLoader.getResource("assets/stylesheets/mobile.css")));
    }

    @Override
    public boolean supports(ApplicationType applicationType) {
        return applicationType == ApplicationType.WEB || applicationType == ApplicationType.WEB_PLUGIN;
    }

    @Override
    public String getCategory() {
        return Category.VIEW;
    }

    @Override
    public String getDocumentation() {
        return "https://www.asset-pipeline.com/manual/";
    }

    @Override
    public boolean shouldApply(ApplicationType applicationType, Options options, Set<Feature> selectedFeatures) {
        return applicationType != ApplicationType.REST_API && applicationType != ApplicationType.PLUGIN;
    }
}
