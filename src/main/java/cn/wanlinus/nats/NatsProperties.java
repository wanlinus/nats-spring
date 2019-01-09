/*
 * Copyright 2018 wanli <wanlinus@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.wanlinus.nats;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wanli
 * @date 2018-11-20 11:38
 */
@ConfigurationProperties(prefix = "spring.nats")
public class NatsProperties {
    private String[] natsUrls = {"nats://127.0.0.1:4200"};
    private String token = null;
    /**
     * Default maximum number of reconnect attempts
     */
    private int maxReconnect = 60;
    /**
     * Default wait time before attempting reconnection to the same server
     */
    private int reconnectWait = 2;
    /**
     * Default connection timeout
     */
    private int connectionTimeout = 2;

    public String[] getNatsUrls() {
        return natsUrls;
    }

    public void setNatsUrls(String[] natsUrls) {
        this.natsUrls = natsUrls;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMaxReconnect() {
        return maxReconnect;
    }

    public void setMaxReconnect(int maxReconnect) {
        this.maxReconnect = maxReconnect;
    }

    public int getReconnectWait() {
        return reconnectWait;
    }

    public void setReconnectWait(int reconnectWait) {
        this.reconnectWait = reconnectWait;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
