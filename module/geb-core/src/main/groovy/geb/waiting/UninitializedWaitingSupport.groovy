/*
 * Copyright 2014 the original author or authors.
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
package geb.waiting

import geb.Initializable

class UninitializedWaitingSupport implements WaitingSupport {
    private final Initializable initializable

    UninitializedWaitingSupport(Initializable initializable) {
        this.initializable = initializable
    }

    @Override
    def <T> T waitFor(Map params = [:], String waitPreset, Closure<T> block) {
        throw initializable.uninitializedException()
    }

    @Override
    def <T> T waitFor(Map params = [:], Closure<T> block) {
        throw initializable.uninitializedException()
    }

    @Override
    def <T> T waitFor(Map params = [:], Number timeout, Closure<T> block) {
        throw initializable.uninitializedException()
    }

    @Override
    def <T> T waitFor(Map params = [:], Number timeout, Number interval, Closure<T> block) {
        throw initializable.uninitializedException()
    }
}
