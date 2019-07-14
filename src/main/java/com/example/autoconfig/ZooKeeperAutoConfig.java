package com.example.autoconfig;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shuc001
 * @Date 2019-07-14 21:58
 */
@Configuration
@EnableConfigurationProperties(ZooKeeperProperties.class)
@ConditionalOnProperty(value = "elasticjob.zookeeper.server-list")
public class ZooKeeperAutoConfig {

    @Autowired
    private ZooKeeperProperties zooKeeperProperties;

    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter zkCenter(){
        ZookeeperConfiguration zc = new ZookeeperConfiguration(zooKeeperProperties.getServerList(), zooKeeperProperties.getNamespace());
        ZookeeperRegistryCenter center = new ZookeeperRegistryCenter(zc);
        System.out.println("init zk");
        //center.init();
        return center;
    }
}
