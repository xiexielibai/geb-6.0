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
package intro

import geb.test.GebSpecWithCallbackServer

class IntroSamplesSpec extends GebSpecWithCallbackServer {

    def "dollar method"() {
        given:
        html {
            div(title: "section", class: "main")
        }

        expect:
        // tag::dollar_examples[]
        $("div") //<1>

        $("div", 0) //<2>

        $("div", title: "section") //<3>

        $("div", 0, title: "section") //<4>

        $("div.main") //<5>

        $("div.main", 0) //<6>
        // end::dollar_examples[]
    }

    def "refining content"() {
        given:
        html {
            div {
                p {
                    table(cellspacing: 0)
                }
            }
        }

        expect:
        // tag::refining_content[]
        $("p", 0).parent() //<1>

        $("p").find("table", cellspacing: '0') //<2>
        // end::refining_content[]
    }
}
