package cn.wanlinus.nats.annotations;

import cn.wanlinus.nats.NatsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable Nats Support {@link NatsSubscribe}
 *
 * @author wanli
 * @date 2018-09-08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({NatsConfiguration.class})
public @interface EnableNats {
}
