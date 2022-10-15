package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfoOK(@PathVariable("id") Integer id) {
		String result = paymentService.paymentInfoOK(id);
		log.info("*******result:" + result);
		return result;
	}

	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeout(@PathVariable("id") Integer id) {
		String result = paymentService.paymentTimeout(id);
		log.info("*******result:" + result);
		return result;
	}

	//服务熔断
	@GetMapping("/payment/circuit/{id}")
	public String paymentCircuitBreaker(@PathVariable("id") Integer id,
										@RequestParam(value = "name", required = false) String name1) {
		System.out.println(name1);
		String result = paymentService.paymentCircuitBreaker(id);
		log.info("*******result:" + result + "  " + serverPort);
		return result + " " + serverPort + "  " + name1;
	}
}