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
package geb.module

import geb.error.InvalidModuleBaseException

class MultipleSelect extends FormElement {

    List<String> getSelected() {
        navigator.value()
    }

    List<String> getSelectedText() {
        selected.collect { $("option", value: it) }*.text()
    }

    void setSelected(List<String> selectedTextOrValues) {
        navigator.value(selectedTextOrValues)
    }

    @Override
    protected void initialized() {
        ensureAtMostOneBaseElement()
        if (!navigator.empty) {
            def tag = navigator.tag()
            if (tag.toLowerCase() == "select") {
                if (!navigator.getAttribute("multiple")) {
                    throw new InvalidModuleBaseException(
                            "Specified base element for ${getClass().name} module was a single choice select but only multiple choice select is allowed as the base element."
                    )
                }
            } else {
                throw new InvalidModuleBaseException(
                        "Specified base element for ${getClass().name} module was '${tag}' but only select is allowed as the base element."
                )
            }
        }
    }
}
