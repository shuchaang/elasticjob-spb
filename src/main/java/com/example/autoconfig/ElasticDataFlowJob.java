package com.example.autoconfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuc001
 * @Date 2019-07-20 16:37
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDataFlowJob {
    String jobName() default "";
    String cron() default "";
    int shardingCount() default 1;
    boolean overwrite() default false;
    boolean streaming() default false;
}
