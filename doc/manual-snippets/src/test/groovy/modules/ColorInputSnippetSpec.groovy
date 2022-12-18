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
package modules

import geb.module.ColorInput
import geb.test.GebSpecWithCallbackServer
import org.openqa.selenium.support.Color

class ColorInputSnippetSpec extends GebSpecWithCallbackServer {

    def "using color input module"() {
        given:
        html """
            // tag::html[]
            <html>
                <body>
                    <input type="color" name="favorite"/>
                </body>
            </html>
            // end::html[]
        """
        // tag::example_color[]
        def input = $(name: "favorite").module(ColorInput)

        // end::example_color[]
        when:
        // tag::example_color[]
        input.color = new Color(0, 255, 0, 1)

        // end::example_color[]
        then:
        // tag::example_color[]
        assert input.color == new Color(0, 255, 0, 1)
        assert input.value() == "#00ff00"

        // end::example_color[]
        when:
        // tag::example_string[]
        input.color = "#ff0000"

        // end::example_string[]
        then:
        // tag::example_string[]
        assert input.value() == "#ff0000"
        assert input.color == new Color(255, 0, 0, 1)
        // end::example_string[]
    }

}
