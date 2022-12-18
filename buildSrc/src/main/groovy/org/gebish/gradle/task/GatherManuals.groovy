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
package org.gebish.gradle.task

import org.gradle.api.artifacts.Configuration
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Sync

class GatherManuals extends Sync {

    void gatherManual(String label, Object files) {
        from(files) {
            into label
        }
    }

    void gatherPublishedManual(String version, String label = version) {
        def versionProvider = project.objects.property(String).value(version)
        gatherPublishedManual(versionProvider, label)
    }

    void gatherPublishedManual(Provider<String> version, String label) {
        gatherZippedManual(label, publishedManual(version))
    }

    private Provider<Configuration> publishedManual(Provider<String> version) {
        version.map {
            def dependency = project.dependencies.create("org.gebish:geb-manual:${it}@zip")
            project.configurations.detachedConfiguration(dependency)
        }
    }

    private void gatherZippedManual(String label, Provider<Configuration> zipConfiguration) {
        gatherManual(label, zipConfiguration.map { project.zipTree(it.singleFile) })
    }
}
