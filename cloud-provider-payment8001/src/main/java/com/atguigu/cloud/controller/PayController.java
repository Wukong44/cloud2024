package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/pay")
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/add")
    public String addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return "" + i;
    }

    @DeleteMapping("/del/{id}")
    public String deletePay(@PathVariable("id") Integer id) {
        payService.delete(id);
        return "" + id;
    }

    @PutMapping("/update")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "" + i;
    }

    @GetMapping("/get/{id}")
    public Pay getPay(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping("/getAll")
    public List<Pay> getAllPay() {
        return payService.list();
    }

}









