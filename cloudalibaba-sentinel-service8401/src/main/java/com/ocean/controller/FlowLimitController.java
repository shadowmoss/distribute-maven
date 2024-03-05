package com.ocean.controller;

import com.ocean.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "-----testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "------testB";
    }
    /**
     * 流控策略:链路
     */
    @Resource
    private FlowLimitService flowLimitService;
    @GetMapping("/testC")
    public String testC(){
        flowLimitService.common();
        return "------testC";
    }
    @GetMapping("/testD")
    public String testD(){
        flowLimitService.common();
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE(){
        System.out.println(System.currentTimeMillis()+"      testE,排队等待");
        return "------testE";
    }
}
