package com.ocean.controller;

import com.ocean.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    /**
     * 测试流量效果的排队等待效果
     * @return
     */
    @GetMapping("/testE")
    public String testE(){
        System.out.println(System.currentTimeMillis()+"      testE,排队等待");
        return "------testE";
    }

    /**
     * 熔断策略测试:慢调用比例测试
     */
    @GetMapping("/testF")
    public String testF(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "------testF 新增熔断测试-慢调用比例";
    }

    /**
     * 熔断规则-异常比例
     * @return
     */
    @GetMapping("/testG")
    public String testG(){
        System.out.println("------测试:新增熔断规则-异常比例");
        int age = 10/0;
        return "------testG,新增熔断规则-异常比例";
    }

    /**
     * 熔断规则-异常数
     * @return
     */
    @GetMapping("/testH")
    public String testH(){
        System.out.println("------测试:新增熔断规则-异常比例");
        int age = 10/0;
        return "------testG,新增熔断规则-异常比例";
    }
}
