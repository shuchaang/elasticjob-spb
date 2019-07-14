package com.example.scej.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.autoconfig.ElasticSimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shuc001
 * @Date 2019-07-14 22:28
 */
@Slf4j
@ElasticSimpleJob(jobName = "demo",cron = "0/10 * * * * ?",shardingCount = 2,overwrite = true)
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("任务名称:"+shardingContext.getJobName());
    }
}
