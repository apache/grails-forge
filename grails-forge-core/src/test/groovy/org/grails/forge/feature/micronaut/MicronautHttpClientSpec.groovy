package org.grails.forge.feature.micronaut

import org.grails.forge.BeanContextSpec
import org.grails.forge.BuildBuilder

class MicronautHttpClientSpec extends BeanContextSpec {


    void "test dependencies"() {
        when:
        final String template = new BuildBuilder(beanContext)
                .features(["micronaut-http-client"])
                .render()

        then:
        template.contains("implementation \"io.micronaut:micronaut-http-client")
        template.contains("implementation \"io.micronaut.serde:micronaut-serde-jackson")
    }
}
