package com.cn.scheduled;

import org.springframework.stereotype.Service;

@Service
public class ScheduledTest {

    //@Scheduled(cron = "0/3 * * * * ? ")  //五秒执行一次
    public void scheduledTest() {
        System.out.println("定时器");
    }

}