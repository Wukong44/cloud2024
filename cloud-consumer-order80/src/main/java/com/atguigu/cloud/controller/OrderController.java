package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {
    // public static final String PaymentSrv_URL = "http://localhost:8001";// 先写死，硬编码
    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String PaymentSrv_URL;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     *
     * @param payDTO
     * @return
     */
    @PostMapping("/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。
    @GetMapping("/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    // @GetMapping("/del/{id}")
    // public ResultData deletePay(@PathVariable("id") Integer id) {
    //     return restTemplate.getForObject(PaymentSrv_URL + "/pay/del/" + id, ResultData.class, id);
    // }

    // @GetMapping("/consumer/pay/get/{id}")
    // public ResultData getPayInfo(@PathVariable("id") Integer id) {
    //     return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    // }


}
