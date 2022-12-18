/*
 * Copyright 2018 the original author or authors.
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

import geb.Page
import geb.test.GebSpecWithCallbackServer

import java.time.OffsetDateTime

import static java.time.temporal.ChronoUnit.MILLIS

class RefreshWaitingSpec extends GebSpecWithCallbackServer {

    def setup() {
        html {
            span(OffsetDateTime.now().toString())
        }
    }

    def "reloading page while waiting"() {
        given:
            // tag::test[]
            def startTimestamp = OffsetDateTime.now()
            to PageWithTimestamp

            // end::test[]
        when:
            // tag::test[]
            refreshWaitFor {
                timestamp > startTimestamp.plus(300, MILLIS)
            }

            // end::test[]
        then:
            // tag::test[]
            assert timestamp > startTimestamp.plus(300, MILLIS)
            // end::test[]
    }

}

// tag::page[]
class PageWithTimestamp extends Page {
    static content = {
        timestamp { OffsetDateTime.parse($().text()) }
    }
}
// end::page[]
