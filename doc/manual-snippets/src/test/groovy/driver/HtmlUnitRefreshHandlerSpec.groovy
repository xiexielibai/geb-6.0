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
package driver

// tag::import[]
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler
// end::import[]
import fixture.Browser
import fixture.DriveMethodSupportingSpecWithServer

class HtmlUnitRefreshHandlerSpec extends DriveMethodSupportingSpecWithServer {
    def "can change refresh handler"() {
        expect:
        // tag::changing_handler[]
        Browser.drive {
            driver.webClient.refreshHandler = new ThreadedRefreshHandler()
            // <1>
        }
        // end::changing_handler[]
    }
}
