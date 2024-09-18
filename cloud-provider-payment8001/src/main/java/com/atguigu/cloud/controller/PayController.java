package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
@Tag(name = "支付微服务模块", description = "支付CRUD")
@Slf4j
public class PayController {
    @Resource
    private PayService payService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
    public ResultData addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return ResultData.success("" + i);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData deletePay(@PathVariable("id") Integer id) {
        payService.delete(id);
        return ResultData.success("" + id);
    }

    @PutMapping("/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return ResultData.success("" + i);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData getPay(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("不能为负数id");
        return ResultData.success(payService.getById(id) + "/n当前服务器端口号：" + serverPort);
    }

    @GetMapping("/getAll")
    public ResultData getAllPay() {
        return ResultData.success(payService.list());
    }

}









