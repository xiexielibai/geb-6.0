/* Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb.content

import geb.error.UndefinedPageContentException
import geb.navigator.Navigator
import geb.navigator.factory.NavigatorFactory

class DefaultPageContentSupport extends PageContentSupport {

    private final PageContentContainer owner
    private final Map<String, PageContentTemplate> contentTemplates
    private final NavigatorFactory navigatorFactory
    private final Navigator navigator

    DefaultPageContentSupport(PageContentContainer owner, Map<String, PageContentTemplate> contentTemplates, NavigatorFactory navigatorFactory, Navigator navigator = null) {
        this.owner = owner
        this.contentTemplates = contentTemplates ?: [:]
        this.navigatorFactory = navigatorFactory
        this.navigator = navigator
    }

    Navigator getNavigator() {
        navigator ?: navigatorFactory.base
    }

    def getContent(String name, Object[] args) {
        def contentTemplate = contentTemplates[name]
        if (contentTemplate) {
            contentTemplate.get(*args)
        } else {
            throw new UndefinedPageContentException(this, name)
        }
    }

    PageContentContainer getOwner() {
        this.owner
    }

    @Override
    Set<String> getContentNames() {
        contentTemplates.keySet()
    }
}