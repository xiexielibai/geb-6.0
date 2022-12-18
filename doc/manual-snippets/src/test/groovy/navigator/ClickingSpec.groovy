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

import geb.Page
import geb.test.GebSpecWithCallbackServer

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ClickingSpec extends GebSpecWithCallbackServer {

    void click() {
        // tag::click[]
        $("a.login").click(LoginPage)
        // end::click[]
    }

    def "clicking"() {
        given:
        html { HttpServletRequest request, HttpServletResponse response ->
            if (request.requestURI =~ /loginPage/) {
                title("login page")
            } else {
                a(class: "login", href: "/loginPage")
            }
        }

        when:
        click()

        then:
        page in LoginPage
    }
}

class LoginPage extends Page {
    static at = { title == "login page" }
}
