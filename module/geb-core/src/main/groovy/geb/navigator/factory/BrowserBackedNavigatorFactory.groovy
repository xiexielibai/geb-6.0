/*
 * Copyright 2012 the original author or authors.
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
package geb.navigator.factory

import geb.Browser
import geb.navigator.DefaultLocator
import geb.navigator.Locator
import geb.navigator.Navigator
import geb.navigator.SearchContextBasedBasicLocator
import geb.waiting.PotentiallyWaitingExecutor
import org.openqa.selenium.By

class BrowserBackedNavigatorFactory extends AbstractNavigatorFactory {

    final Locator locator

    BrowserBackedNavigatorFactory(Browser browser, InnerNavigatorFactory innerNavigatorFactory) {
        super(browser, innerNavigatorFactory)
        locator = new DefaultLocator(new SearchContextBasedBasicLocator(browser.driver, this))
    }

    @Override
    Navigator getBase() {
        new PotentiallyWaitingExecutor(browser.config.baseNavigatorWaiting).execute { createBase() }
    }

    protected String getBaseXPathExpression() {
        "/*"
    }

    protected Navigator createBase() {
        createFromWebElements(Collections.singletonList(browser.driver.findElement(By.xpath(baseXPathExpression))))
    }
}
