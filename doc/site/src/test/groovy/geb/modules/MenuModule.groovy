/*
 * Copyright 2017 the original author or authors.
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
package geb.modules

import geb.Module

class MenuModule extends Module {
    static content = {
        manuals { expandableMenuItem(2, "manuals-menu") }
        apis { expandableMenuItem(3, "apis-menu") }
    }

    private ExpandableMenuItemModule expandableMenuItem(int index, String linksContainerId) {
        $("a", index).module(new ExpandableMenuItemModule(linksContainerId: linksContainerId))
    }
}