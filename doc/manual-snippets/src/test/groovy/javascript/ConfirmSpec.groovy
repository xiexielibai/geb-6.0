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
package javascript

import geb.test.GebSpecWithCallbackServer

class ConfirmSpec extends GebSpecWithCallbackServer {

    def "confirm"() {
        given:
        html """
            <html>
                // tag::confirm_html[]
                <input type="button" name="showConfirm" onclick="confirm('Do you like Geb?');" />
                // end::confirm_html[]
            </html>
        """

        expect:
        // tag::confirm[]
        assert withConfirm(true) { $("input", name: "showConfirm").click() } == "Do you like Geb?"
        // end::confirm[]
    }

    def "no confirm"() {
        given:
        html """
            <html>
                // tag::no_confirm_html[]
                <input type="button" name="dontShowConfirm" />
                // end::no_confirm_html[]
            </html>
        """

        when:
        // tag::no_confirm[]
        withNoConfirm { $("input", name: "dontShowConfirm").click() }
        // end::no_confirm[]

        then:
        noExceptionThrown()
    }
}
