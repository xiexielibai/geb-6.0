/*
 * Copyright 2021 the original author or authors.
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
package geb.spock

import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FieldInfo

class FieldBackedIterationInterceptor implements IMethodInterceptor {

    private final FieldInfo fieldInfo

    FieldBackedIterationInterceptor(FieldInfo fieldInfo) {
        this.fieldInfo = fieldInfo
    }

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        def interceptor = fieldInfo.readValue(invocation.instance) as IMethodInterceptor
        interceptor.intercept(invocation)
    }
}
