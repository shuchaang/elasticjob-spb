package com.example.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shuc001
 * @Date 2019-07-14 21:49
 */
@ConfigurationProperties(prefix = "elasticjob.zookeeper")
@Data
public class ZooKeeperProperties {
    private String serverList;
    private String namespace;
}
