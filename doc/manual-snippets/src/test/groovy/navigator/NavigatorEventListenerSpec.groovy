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
package navigator

import configuration.InlineConfigurationLoader
import geb.test.GebSpecWithCallbackServer

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut

class NavigatorEventListenerSpec extends GebSpecWithCallbackServer implements InlineConfigurationLoader {

    @SuppressWarnings("GStringExpressionWithinString")
    String getNavigatorEventListenerConfiguration() {
        '''
            // tag::config[]
            import geb.Browser
            import geb.navigator.Navigator
            import geb.navigator.event.NavigatorEventListenerSupport

            navigatorEventListener = new NavigatorEventListenerSupport() {
                void afterClick(Browser browser, Navigator navigator) {
                    println "${navigator*.tag()} was clicked"
                }
            }
            // end::config[]
        '''
    }

    def "registering a reporting listener"() {
        when:
        configScript(navigatorEventListenerConfiguration)
        browser.config.merge(config)

        and:
        html {
            button "button"
        }

        and:
        def output = tapSystemOut { $("button").click() }

        then:
        output =~ /\[button\] was clicked/
    }
}
