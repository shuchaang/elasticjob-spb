package com.example.scej.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.autoconfig.ElasticSimpleJob;
import com.example.mapper.Order;
import com.example.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * @author shuc001
 * @Date 2019-07-14 22:28
 */
@Slf4j
@ElasticSimpleJob(jobName = "simple_order",cron = "* 0/1 * * * ?",
        shardingCount = 1,overwrite = true)
public class MySimpleJob implements SimpleJob {

    @Autowired
    private OrderMapper mapper;

    @Override
    public void execute(ShardingContext shardingContext) {
        Calendar c= Calendar.getInstance();
        c.add(Calendar.SECOND,-30);
        //分奇数偶数
        List<Order> orders = mapper.selectAll(c.getTime());
        for (Order order : orders) {
            log.info("处理订单:{}",order);
            mapper.updateStatus(order.getId());
        }
    }
}
