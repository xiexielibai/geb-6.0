/*
 * Copyright 2020 the original author or authors.
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
package geb.junit5

import geb.test.GebTestManager
import geb.test.GebTestManagerBuilder
import geb.transform.DynamicallyDispatchesToBrowser
import org.junit.jupiter.api.extension.ExtendWith

@DynamicallyDispatchesToBrowser
@ExtendWith(GebTestManagerExtension)
class GebTest {

    private final static GebTestManager TEST_MANAGER = new GebTestManagerBuilder().build()

    @Delegate(includes = ["getBrowser"])
    static GebTestManager getTestManager() {
        TEST_MANAGER
    }

}
