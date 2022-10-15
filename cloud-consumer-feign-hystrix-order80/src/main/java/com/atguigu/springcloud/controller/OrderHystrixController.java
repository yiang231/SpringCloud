package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
	@Autowired
	private PaymentHystrixService paymentHystrixService;

	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentInfoOK(@PathVariable("id") Integer id) {
		//int i = 10/0;
		String result = paymentHystrixService.paymentInfoOK(id);
		log.info("*******result:" + result);
		return result;
	}

	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeout(@PathVariable("id") Integer id) {
		String result = paymentHystrixService.paymentInfoTimeout(id);
		log.info("*******result:" + result);
		return result;
	}
}
