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
package fixture

import org.openqa.selenium.htmlunit.HtmlUnitDriver

class Browser extends geb.Browser {
    static String serverBaseUrl

    Browser() {
        browser.baseUrl = serverBaseUrl
    }

    static Browser drive(Map browserProperties = [:], @DelegatesTo(Browser) Closure script) {
        def browser = new Browser(browserProperties)
        def driver = browser.driver
        if (driver instanceof HtmlUnitDriver) {
            driver.javascriptEnabled = true
        }
        drive(browser, script)
    }
}
