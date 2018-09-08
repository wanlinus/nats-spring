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

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import java.io.IOException;

/**
 * Nats配置类
 *
 * @author wanli
 * @date 2018-09-08
 */
@Configuration
@EnableConfigurationProperties(NatsProperties.class)
public class NatsConfiguration {

    /**
     * Nats Connection config {@link Options}
     *
     * @param properties {@link NatsProperties}
     * @return Nats Connection {@link Connection}
     * @throws IOException          Get Nats Connection throws
     * @throws InterruptedException Get Nats Connection throws
     */
    @Bean
    public Connection connection(NatsProperties properties) throws IOException, InterruptedException {
        Options.Builder builder = new Options.Builder();
        for (String str : properties.getUrls()) {
            builder = builder.server(str);
        }
        builder = builder.connectionName(properties.getConnectionName())
                .sslContext(properties.getSslContext())
                .reconnectWait(properties.getReconnectWait())
                .connectionTimeout(properties.getReconnectWait())
                .pingInterval(properties.getPingInterval())
                .requestCleanupInterval(properties.getRequestCleanupInterval())
                .maxPingsOut(properties.getMaxPingsOut())
                .reconnectBufferSize(properties.getReconnectBufferSize())
                .userInfo(properties.getUsername(), properties.getPassword())
                .token(properties.getToken())
                .bufferSize(properties.getBufferSize());

        if (!properties.isNoRandomize()) {
            builder = builder.noRandomize();
        }
        if (!properties.isVerbose()) {
            builder = builder.verbose();
        }
        if (!properties.isPedantic()) {
            builder = builder.pedantic();
        }
        if (!properties.isUseOldRequestStyle()) {
            builder = builder.oldRequestStyle();
        }
        if (!properties.isTrackAdvancedStats()) {
            builder = builder.turnOnAdvancedStats();
        }
        if (!properties.isNoEcho()) {
            builder = builder.noEcho();
        }
        return Nats.connect(builder.build());
    }

    /**
     * 在SpringBoot启动时装配，并设置订阅类
     *
     * @param connection Nats链接
     * @return {@link NatsConfigBeanPostProcessor}
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public NatsConfigBeanPostProcessor configBeanPostProcessor(Connection connection) {
        return new NatsConfigBeanPostProcessor(connection);
    }
}
