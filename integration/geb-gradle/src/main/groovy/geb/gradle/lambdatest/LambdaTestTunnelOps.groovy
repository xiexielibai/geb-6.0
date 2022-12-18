/*
 * Copyright 2019 the original author or authors.
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
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.testing.Test

class LambdaTestTunnelOps implements TestTaskConfigurer {

    public static final String TUNNEL_NAME_ENV_VAR = "GEB_LAMBDATEST_TUNNEL_NAME"

    private final Property<String> infoAPIPortProperty

    String tunnelReadyMessage = 'You can start testing now'

    String tunnelName
    List<String> allowHosts
    List<String> bypassHosts
    String callbackURL
    String config
    String clientCert
    String clientKey
    String dir
    String dns
    boolean egressOnly
    String env
    boolean ingressOnly
    boolean loadBalanced
    String logFile
    boolean mitm
    String mode
    List<String> mTLSHosts
    List<String> noProxy
    String pidfile
    String port
    String proxyhost
    String proxypass
    String proxyport
    String proxyuser
    String pacfile
    boolean sharedtunnel
    String sshConnType
    boolean version

    List<String> additionalOptions = []

    LambdaTestTunnelOps(Project project) {
        this.infoAPIPortProperty = project.objects.property(String)
        this.infoAPIPortProperty.set(project.providers.provider(new FreePortNumberProvider()))
    }

    void configure(Test test) {
        test.environment(TUNNEL_NAME_ENV_VAR, tunnelName)
    }

    void setInfoAPIPort(String infoAPIPort) {
        infoAPIPortProperty.set(infoAPIPort)
    }

    String getInfoAPIPort() {
        infoAPIPortProperty.get()
    }
}
