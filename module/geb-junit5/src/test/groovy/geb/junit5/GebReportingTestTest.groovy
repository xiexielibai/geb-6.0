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

import geb.Page
import geb.junit5.fixture.CallbackServerExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class GebReportingTestTest extends GebReportingTest {

    @RegisterExtension
    public static CallbackServerExtension callbackServerExtension = new CallbackServerExtension(testManager)

    @BeforeAll
    static void setupClass() {
        callbackServerExtension.server.html {}
    }

    @Test
    void missingMethodsAreInvokedOnTheDriverInstance() {
        // This also verifies that the driver instance is instantiated correctly
        go("/")
    }

    @Test
    void missingPropertyAccessesAreRequestedOnTheDriverInstance() {
        page GebReportingTestTestPage
        assert prop == 1
    }

    @Test
    void missingPropertyAssignmentsAreForwardedToTheDriverInstance() {
        page GebReportingTestTestPage
        prop = 2
        assert prop == 2
    }
}

class GebReportingTestTestPage extends Page {
    int prop = 1
}
