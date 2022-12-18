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

import fixture.DriveMethodSupportingSpecWithServer
import geb.Module

abstract class FormContentSpec extends DriveMethodSupportingSpecWithServer {

    def setup() {
        server.html {
            form(id: "personal-data") {
                input(id: "button", type: "button", "click me!")
            }
            script(type: "text/javascript", """
                var button = document.getElementById("button");
                button.onclick = function () {
                    button.className = "clicked";
                };
            """)
        }
    }
}

// tag::form_module[]
class FormModule extends Module {
    static content = {
        button { $("input", type: "button") }
    }
}
// end::form_module[]