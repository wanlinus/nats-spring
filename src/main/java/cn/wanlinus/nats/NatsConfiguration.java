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
import java.time.Duration;

/**
 * Nats配置类
 *
 * @author wanli
 * @date 2018-09-08
 */
@Configuration
@EnableConfigurationProperties(NatsProperties.class)
public class NatsConfiguration {

    @Bean
    public Connection natsConnection(NatsProperties properties) throws IOException, InterruptedException {
        Options.Builder builder = new io.nats.client.Options.Builder()
                .servers(properties.getNatsUrls())
                .token(properties.getToken())
                .connectionListener(new NatsListener())
                .maxReconnects(properties.getMaxReconnect())
                .reconnectWait(Duration.ofSeconds(properties.getReconnectWait()))
                .connectionTimeout(Duration.ofSeconds(properties.getConnectionTimeout()));
        return Nats.connect(builder.build());
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public NatsConfigBeanPostProcessor configBeanPostProcessor(Connection connection) {
        return new NatsConfigBeanPostProcessor(connection);
    }
}
