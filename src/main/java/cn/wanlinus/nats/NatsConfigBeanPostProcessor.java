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

import cn.wanlinus.nats.annotations.NatsSubscribe;
import cn.wanlinus.nats.exception.NatsException;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Nats Bean处理类 主要是注册{@link NatsSubscribe}
 *
 * @author wanli
 * @date 2018-09-08
 */
public class NatsConfigBeanPostProcessor implements BeanPostProcessor {

    private Connection connection;

    public NatsConfigBeanPostProcessor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        final Class<?> clazz = bean.getClass();
        for (final Method method : clazz.getMethods()) {
            final NatsSubscribe subscribe = AnnotationUtils.findAnnotation(method, NatsSubscribe.class);
            if (subscribe != null) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1 || !parameterTypes[0].equals(Message.class)) {
                    throw new NatsException(String.format(
                            "Method '%s' on bean with name '%s' must have a single parameter of type %s when using the @%s annotation.",
                            method.toGenericString(),
                            beanName,
                            Message.class.getName(),
                            NatsSubscribe.class.getName()
                    ));
                }
                Dispatcher dispatcher = connection.createDispatcher(message -> {
                    try {
                        method.invoke(bean, message);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
                dispatcher.subscribe(subscribe.value());
            }
        }
        return bean;
    }
}
