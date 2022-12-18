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

import geb.Browser
import geb.junit5.fixture.TestRunner
import org.junit.jupiter.api.Test

class GebTestCleanupTest {

    @Test
    void browserCookiesAreCleanedUpAfterEachTest() {
        TestRunner.runSuccessfully("""
            import geb.junit5.GebTest
            import geb.junit5.fixture.CallbackServerExtension
            import javax.servlet.http.Cookie
            import org.junit.jupiter.api.*
            import org.junit.jupiter.api.extension.RegisterExtension

            class GebTestCleanupTestTest extends GebTest {

                @RegisterExtension
                public static CallbackServerExtension callbackServerExtension = new CallbackServerExtension(testManager)

                @Test
                void goToAPageToSetACookie() {
                    callbackServerExtension.server.get = { req, res ->
                        res.contentType = "text/plain"
                        res.addCookie(new Cookie("a", "1"))
                        res.outputStream << "cookies set"
                    }

                    go()
                }

            }
        """)

        assert !new Browser().driver.manage().cookies
    }

}
