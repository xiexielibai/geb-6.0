/*
 * Copyright 2019 the original author or authors.
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
package geb.collection

import spock.lang.Specification
import spock.lang.Unroll

class FilteringIteratorSpec extends Specification {

    @Unroll
    def "filtering on predicate looking for non-null values"() {
        expect:
        new FilteringIterator(original.iterator(), { it != null }).toList() == notNull

        where:
        original                 | notNull
        []                       | []
        [null]                   | []
        [1]                      | [1]
        [null, 1, null]          | [1]
        [1, 2]                   | [1, 2]
        [null, null, 1, null, 2] | [1, 2]
        [1, null, 2, null, null] | [1, 2]
        [1, null, null, 2]       | [1, 2]
    }

    @Unroll
    def "filtering on predicate looking for null values"() {
        expect:
        new FilteringIterator(original.iterator(), { it == null }).toList() == nulls

        where:
        original                 | nulls
        []                       | []
        [null]                   | [null]
        [1]                      | []
        [null, 1, null]          | [null, null]
        [1, 2]                   | []
        [null, null, 1, null, 2] | [null, null, null]
        [1, null, 2, null, null] | [null, null, null]
        [1, null, null, 2]       | [null, null]
    }

}
