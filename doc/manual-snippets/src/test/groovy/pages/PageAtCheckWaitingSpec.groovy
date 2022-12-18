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
package pages

import geb.Page
import geb.test.GebSpecWithCallbackServer

class PageAtCheckWaitingSpec extends GebSpecWithCallbackServer {

    def "using page level atCheckWaiting"() {
        given:
        html {
            script(type: "text/javascript", """
                setTimeout(function() {
                    document.title = "Example Page"
                }, 100)
            """)
        }

        when:
        to PageWithAtCheckWaiting

        then:
        noExceptionThrown()
    }
}

// tag::page[]
class PageWithAtCheckWaiting extends Page {
    static atCheckWaiting = true
    // end::page[]
    static at = { title == "Example Page" }
    // tag::page[]
}
// end::page[]