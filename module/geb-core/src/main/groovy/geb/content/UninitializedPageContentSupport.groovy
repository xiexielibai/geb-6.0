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
package geb.content

import geb.Initializable
import geb.navigator.Navigator

class UninitializedPageContentSupport extends PageContentSupport {
    private final Initializable initializable

    UninitializedPageContentSupport(Initializable initializable) {
        this.initializable = initializable
    }

    @Override
    def getContent(String name, Object[] args) {
        throw initializable.uninitializedException()
    }

    @Override
    Navigator getNavigator() {
        throw initializable.uninitializedException()
    }

    @Override
    PageContentContainer getOwner() {
        throw initializable.uninitializedException()
    }

    @Override
    Set<String> getContentNames() {
        throw  initializable.uninitializedException()
    }
}
