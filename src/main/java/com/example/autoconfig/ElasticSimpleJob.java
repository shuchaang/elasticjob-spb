package com.example.autoconfig;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuc001
 * @Date 2019-07-14 22:29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ElasticSimpleJob {
    String jobName() default "";
    String cron() default "";
    int shardingCount() default 1;
    boolean overwrite() default false;
}
