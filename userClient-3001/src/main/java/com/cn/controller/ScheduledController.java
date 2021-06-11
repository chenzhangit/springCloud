package com.cn.controller;

import com.cn.scheduled.ScheduledTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduled")
public class ScheduledController {

    @Autowired
    private ScheduledTest scheduledTest;

    @GetMapping("/test")
    public void test(){
    }

}
