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
package geb.gradle.lambdatest

import geb.gradle.cloud.TestTaskConfigurer
import org.gradle.api.tasks.testing.Test

class LambdaTestAccount implements TestTaskConfigurer {
    public static final String USER_ENV_VAR = "GEB_LAMBDATEST_USERNAME"
    public static final String ACCESS_KEY_ENV_VAR = "GEB_LAMBDATEST_AUTHKEY"

    String username
    String accessKey

    void configure(Test test) {
        if (username) {
            test.environment(USER_ENV_VAR, username)
        }
        if (accessKey) {
            test.environment(ACCESS_KEY_ENV_VAR, accessKey)
        }
    }
}
