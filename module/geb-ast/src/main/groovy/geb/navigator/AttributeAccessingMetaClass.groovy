/*
 * Copyright 2010 the original author or authors.
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
package geb.navigator

/**
 * A delegating meta class implementation that intercepts field access using the .@ operator and sends it to getProperty("@$name")
 */
class AttributeAccessingMetaClass extends DelegatingMetaClass {

    AttributeAccessingMetaClass(MetaClass delegate) {
        super(delegate)
    }

    Object getAttribute(Object object, String attribute) {
        if (object instanceof GroovyObject) {
            object.getProperty("@$attribute")
        } else {
            super.getAttribute(object, attribute)
        }
    }

}
