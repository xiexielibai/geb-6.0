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
package modules

import geb.module.RadioButtons
import geb.test.GebSpecWithCallbackServer

class RadioButtonsSnippetSpec extends GebSpecWithCallbackServer {

    def "using text input module"() {
        given:
        html """
            // tag::html[]
            <html>
                <body>
                    <label for="site-current">Search this site</label>
                    <input type="radio" id="site-current" name="site" value="current">

                    <label for="site-google">Search Google
                        <input type="radio" id="site-google" name="site" value="google">
                    </label>
                </body>
            </html>
            // end::html[]
        """

        when:
        // tag::example[]
        def radios = $(name: "site").module(RadioButtons)
        radios.checked = "current"

        // end::example[]
        then:
        // tag::example[]
        assert radios.checked == "current"
        assert radios.checkedLabel == "Search this site"

        // end::example[]
        when:
        // tag::example[]
        radios.checked = "Search Google"

        // end::example[]
        then:
        // tag::example[]
        assert radios.checked == "google"
        assert radios.checkedLabel == "Search Google"
        // end::example[]
    }
}
