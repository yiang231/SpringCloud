package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

	@HystrixCommand(fallbackMethod = "paymentTimeoutFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeout(@PathVariable("id") Integer id) {
		String result = paymentHystrixService.paymentInfoTimeout(id);
		log.info("*******result:" + result);
		return result;
	}

	//兜底方法，上面方法出问题,我来处理，返回一个出错信息
	public String paymentTimeoutFallback(Integer id) {
		return "特定托底方法：我是消费者80,对方支付系统繁忙稍后再试。或自己运行出错，请检查自己。";
	}
}
