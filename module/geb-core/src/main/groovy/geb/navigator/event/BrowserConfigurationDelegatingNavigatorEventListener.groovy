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
package geb.navigator.event

import geb.Browser
import geb.navigator.Navigator

import java.util.function.Supplier

class BrowserConfigurationDelegatingNavigatorEventListener extends DelegatingNavigatorEventListener {

    BrowserConfigurationDelegatingNavigatorEventListener(Browser browser, Navigator source) {
        super({ browser.config.navigatorEventListener } as Supplier<NavigatorEventListener>, source)
    }

}
