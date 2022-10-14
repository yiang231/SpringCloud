package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
	@Value("${server.port}")
	private String serverPort;
	@Resource
	private PaymentService paymentService;

	@PostMapping(value = "/payment/create")
	public CommonResult<Integer> create(@RequestBody Payment payment) { //埋雷
		int result = paymentService.create(payment);
		log.info("*****插入结果：" + result);
		if (result > 0) {  //成功
			return new CommonResult(200, "插入数据库成功" + "  " + serverPort, result);
		} else {
			return new CommonResult(444, "插入数据库失败" + "  " + serverPort);
		}
	}

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("*****查询结果：" + payment);
		if (payment != null) {  //说明有数据，能查询成功
			return new CommonResult(200, "查询成功" + "  " + serverPort, payment);
		} else {
			return new CommonResult(444, "没有对应记录，查询ID：" + id + "  " + serverPort);
		}
	}

	@GetMapping("/payment/zipkin")
	public String paymentZipkin() {
		return "hi ,i'am paymentzipkin server，welcome to atguigu，O(∩_∩)O哈哈~";
	}
}