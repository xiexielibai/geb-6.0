/*
 * Copyright 2020 the original author or authors.
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
package geb.gradle.lambdatest.task

import geb.gradle.cloud.ExternalTunnel
import geb.gradle.lambdatest.LambdaTestTunnelOps
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

class StopLambdaTestTunnel extends DefaultTask {

    @Internal
    LambdaTestTunnelOps lambdaTestTunnelOps

    @Internal
    ExternalTunnel tunnel

    @TaskAction
    void stop() {
        if (lambdaTestTunnelOps.infoAPIPort) {
            stopTunnelUsingHttpRequest()
        } else {
            tunnel.stopTunnel()
        }
    }

    private void stopTunnelUsingHttpRequest() {
        def responseCode = sendDeleteRequestToStopEndpoint()

        if (responseCode != 200) {
            throw new RuntimeException("Unexpected response status from tunnel stop endpoint: ${responseCode}")
        }
    }

    private int sendDeleteRequestToStopEndpoint() {
        def url = new URL("http://127.0.0.1:${lambdaTestTunnelOps.infoAPIPort}/api/v1.0/stop")
        def connection = url.openConnection() as HttpURLConnection
        def closeableConnection = { connection.disconnect() } as Closeable

        closeableConnection.withCloseable {
            connection.requestMethod = "DELETE"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.responseCode
        }
    }
}
