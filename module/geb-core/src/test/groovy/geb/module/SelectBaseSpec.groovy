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
package geb.module

import geb.error.InvalidModuleBaseException
import geb.test.GebSpecWithCallbackServer

class SelectBaseSpec extends GebSpecWithCallbackServer {

    def "can base a select on an empty navigator"() {
        given:
        html {
        }

        when:
        $("select").module(Select)

        then:
        noExceptionThrown()
    }

    def "basing a select on anything other than a select element throws an exception"() {
        given:
        html {
            input()
        }

        when:
        $("input").module(Select)

        then:
        InvalidModuleBaseException e = thrown()
        e.message == "Specified base element for ${Select.name} module was 'input' but only select is allowed as the base element."
    }

    def "basing a select on multiple choice select throws an exception"() {
        given:
        html {
            select(multiple: true)
        }

        when:
        $("select").module(Select)

        then:
        InvalidModuleBaseException e = thrown()
        e.message == "Specified base element for ${Select.name} module was a multiple choice select but only single choice select is allowed as the base element."
    }

    def "can base a select on a single choice select"() {
        given:
        html {
            select()
        }

        when:
        $("select").module(Select)

        then:
        noExceptionThrown()
    }

    def "creating the module with a base navigator containing more than one element results in error"() {
        given:
        html {
            select()
            select()
        }

        when:
        $("select").module(Select)

        then:
        InvalidModuleBaseException e = thrown()
        e.message == "Specified base navigator for ${Select.name} module has 2 elements but at most one element is allowed."
    }
}
