package org.grails.forge.cli

import io.micronaut.context.BeanContext
import io.micronaut.core.util.functional.ThrowingSupplier
import org.grails.forge.application.ApplicationType
import org.grails.forge.application.OperatingSystem
import org.grails.forge.application.generator.ProjectGenerator
import org.grails.forge.io.ConsoleOutput
import org.grails.forge.io.FileSystemOutputHandler
import org.grails.forge.io.OutputHandler
import org.grails.forge.options.BuildTool
import org.grails.forge.options.Language
import org.grails.forge.options.Options
import org.grails.forge.options.TestFramework
import org.grails.forge.util.NameUtils

trait CommandFixture {

    abstract BeanContext getBeanContext()

    abstract File getDir()

    void generateProject(ApplicationType applicationType = ApplicationType.DEFAULT_OPTION,
                         String[] features) {
        beanContext.getBean(ProjectGenerator).generate(applicationType,
                NameUtils.parse("example.grails.foo"),
                new Options(),
                OperatingSystem.MACOS_ARCH64,
                features.toList(),
                new FileSystemOutputHandler(dir, ConsoleOutput.NOOP),
                ConsoleOutput.NOOP)
    }

    ThrowingSupplier<OutputHandler, IOException> getOutputHandler(ConsoleOutput consoleOutput) {
        return () -> new FileSystemOutputHandler(dir, consoleOutput)
    }

}