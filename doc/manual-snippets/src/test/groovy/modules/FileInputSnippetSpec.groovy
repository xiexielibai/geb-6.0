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

import geb.module.FileInput
import geb.test.GebSpecWithCallbackServer

class FileInputSnippetSpec extends GebSpecWithCallbackServer {

    def "using text input module"() {
        given:
        html """
            // tag::html[]
            <html>
                <body>
                    <input type="file" name="csv"/>
                </body>
            </html>
            // end::html[]
        """

        when:
        // tag::example[]
        def csvFile = new File("data.csv")
        def input = $(name: "csv").module(FileInput)

        input.file = csvFile
        // end::example[]
        then:
        assert input.value().endsWith(csvFile.name)
    }
}
