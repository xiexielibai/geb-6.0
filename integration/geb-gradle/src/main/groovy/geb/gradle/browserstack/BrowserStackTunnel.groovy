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
package geb.gradle.browserstack

import geb.gradle.cloud.ExternalTunnel
import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import org.slf4j.Logger

class BrowserStackTunnel extends ExternalTunnel {
    private static final String HTTPS_PROTOCOL = 'https'

    final BrowserStackExtension extension

    final String outputPrefix = 'browserstack-tunnel'

    BrowserStackTunnel(Project project, Logger logger, BrowserStackExtension extension) {
        super(project, logger)
        this.extension = extension
    }

    static String assembleAppSpecifier(List<URL> applicationUrls) {
        applicationUrls.collect { "${it.host},${determinePort(it)},${it.protocol == HTTPS_PROTOCOL ? '1' : '0'}" }.join(',')
    }

    static int determinePort(URL url) {
        url.port > 0 ? url.port : (url.protocol == HTTPS_PROTOCOL ? 443 : 80)
    }

    @Override
    void validateState() {
        if (!extension.account.accessKey) {
            throw new InvalidUserDataException('No BrowserStack access key set')
        }
    }

    @Override
    List<String> assembleCommandLine() {
        def tunnelPath = project.fileTree(project.tasks.unzipBrowserStackTunnel.outputs.files.singleFile).singleFile.absolutePath
        def commandLine = [tunnelPath]
        commandLine << extension.account.accessKey
        if (extension.local.identifier) {
            commandLine << '-localIdentifier' << extension.local.identifier
        }
        if (extension.applicationUrls) {
            commandLine << "-only" << assembleAppSpecifier(extension.applicationUrls)
        }
        if (extension.local.proxyHost) {
            commandLine << "-proxyHost" << extension.local.proxyHost
        }
        if (extension.local.proxyPort) {
            commandLine << "-proxyPort" << extension.local.proxyPort
        }
        if (extension.local.proxyUser) {
            commandLine << "-proxyUser" << extension.local.proxyUser
        }
        if (extension.local.proxyPass) {
            commandLine << "-proxyPass" << extension.local.proxyPass
        }
        if (extension.local.force) {
            commandLine << "-forcelocal"
        }
        commandLine.addAll(extension.local.additionalOptions)
        commandLine
    }

    @Override
    String getTunnelReadyMessage() {
        extension.local.tunnelReadyMessage
    }
}
