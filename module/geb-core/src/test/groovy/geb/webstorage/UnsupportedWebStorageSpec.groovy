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
package geb.webstorage

import geb.error.WebStorageNotSupportedException
import geb.test.GebSpec

class UnsupportedWebStorageSpec extends GebSpec {

    def "an informative exception is thrown when web storage is not supported by the driver and trying to access local storage"() {
        when:
        localStorage

        then:
        thrown(WebStorageNotSupportedException)
    }

    def "an informative exception is thrown when web storage is not supported by the driver and trying to access session storage"() {
        when:
        sessionStorage

        then:
        thrown(WebStorageNotSupportedException)
    }

}
