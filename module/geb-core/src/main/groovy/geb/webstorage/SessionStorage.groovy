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

import org.openqa.selenium.html5.SessionStorage as SeleniumSessionStorage
import org.openqa.selenium.html5.WebStorage as SeleniumWebStorage

class SessionStorage implements WebStorage {

    private final SeleniumWebStorage webDriver

    SessionStorage(SeleniumWebStorage webDriver) {
        this.webDriver = webDriver
    }

    @Override
    String getAt(String key) {
        sessionStorage.getItem(key)
    }

    @Override
    void putAt(String key, String value) {
        sessionStorage.setItem(key, value)
    }

    @Override
    void remove(String key) {
        sessionStorage.removeItem(key)
    }

    @Override
    Set<String> keySet() {
        sessionStorage.keySet()
    }

    @Override
    int size() {
        sessionStorage.size()
    }

    @Override
    void clear() {
        sessionStorage.clear()
    }

    private SeleniumSessionStorage getSessionStorage() {
        webDriver.sessionStorage
    }

}
