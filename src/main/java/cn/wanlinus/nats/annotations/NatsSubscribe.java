package cn.wanlinus.nats.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wanli
 * @date 2018-09-08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NatsSubscribe {

    /**
     * @return @see {@link #subscribe()}
     */
    @AliasFor("subscribe")
    String value() default "";

    /**
     * The Nats subject to subscribe to.
     *
     * @return the Nats Subject
     */
    @AliasFor("value")
    String subscribe() default "";
}
