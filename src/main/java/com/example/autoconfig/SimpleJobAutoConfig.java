package com.example.autoconfig;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author shuc001
 * @Date 2019-07-14 22:33
 */
@Configuration
@ConditionalOnBean(CoordinatorRegistryCenter.class)
@AutoConfigureAfter(ZooKeeperAutoConfig.class)
public class SimpleJobAutoConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CoordinatorRegistryCenter center;

    @PostConstruct
    public void initSimpleJob(){
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticSimpleJob.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object instance = entry.getValue();
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            for (Class<?> anInterface : interfaces) {
                if ( anInterface == SimpleJob.class){
                    //注册
                    ElasticSimpleJob anno = instance.getClass().getAnnotation(ElasticSimpleJob.class);
                    String jobName =anno.jobName();
                    String cron = anno.cron();
                    int count = anno.shardingCount();
                    boolean overwrite = anno.overwrite();


                    JobCoreConfiguration core = JobCoreConfiguration.newBuilder(jobName, cron, count).build();
                    SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(core, instance.getClass().getCanonicalName());
                    LiteJobConfiguration build = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(overwrite).build();

                    new JobScheduler(center,build).init();
                }
            }
        }
    }
}
