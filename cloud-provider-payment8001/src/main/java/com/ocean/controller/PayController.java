package com.ocean.controller;

import com.ocean.enums.ReturnCodeEnum;
import com.ocean.resp.ResultData;
import com.ocean.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import com.ocean.dto.PayDTO;
import com.ocean.entities.Pay;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="支付微服务模块",description = "支付CBD")
public class PayController {
    @Resource
    PayService payService;
    @PostMapping(value = "/pay/add")
    @Operation(description = "添加支付记录")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录,返回值:"+i);
    }
    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(description = "")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        return ResultData.success(payService.delete(id));
    }
    @PutMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值："+i);
    }
    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if(id == -4){
            throw new RuntimeException("id 不能为负数");
        }
        return ResultData.success(payService.getById(id));
    }
    //全部查询getall作为家庭作业
    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getPayError(){
        Integer integer = Integer.valueOf(200);
        try{
            System.out.println("come in payerror test");
            int age = 10/0;
        }catch(Exception e){
            e.printStackTrace();
            ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return ResultData.success(integer);
    }
}
