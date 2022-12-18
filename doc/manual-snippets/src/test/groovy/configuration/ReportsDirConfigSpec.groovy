/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package configuration

import spock.lang.Specification

class ReportsDirConfigSpec extends Specification implements InlineConfigurationLoader {

    def "using path as reports dir value"() {
        when:
        configScript """
            // tag::using_path[]
            reportsDir = "target/geb-reports"
            // end::using_path[]
        """

        then:
        config.reportsDir == new File("target/geb-reports")
    }

    def "using file as reports dir value"() {
        when:
        configScript """
            // tag::using_file[]
            reportsDir = new File("target/geb-reports")
            // end::using_file[]
        """

        then:
        config.reportsDir == new File("target/geb-reports")
    }
}