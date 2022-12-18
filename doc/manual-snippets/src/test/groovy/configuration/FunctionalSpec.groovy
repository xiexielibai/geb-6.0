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

// tag::runtime_override[]
import geb.spock.GebReportingSpec

class FunctionalSpec extends GebReportingSpec {

    def setupSpec() {
        browser.config.autoClearCookies = false
    }

    def cleanup() {
        browser.config.autoClearCookies = true
    }
    // end::runtime_override[]
    def "configuration is changed"() {
        expect:
        !browser.config.autoClearCookies
    }
    // tag::runtime_override[]
}
// end::runtime_override[]