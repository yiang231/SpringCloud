package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
	private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/";//8001 8002 8003
	@Autowired
	private RestTemplate restTemplate; //SpringBoot启动时并没有创建RestTemplate对象，此时运行出现NullPointerException

	@PostMapping(value = "/consumer/payment/create")
	public CommonResult<Integer> create(@RequestBody Payment payment) {
		CommonResult commonResult =
				this.restTemplate.postForObject(PAYMENT_URL + "payment/create", payment, CommonResult.class);
		return commonResult;
	}

	@GetMapping(value = "/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		CommonResult commonResult =
				this.restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
		return commonResult;
	}
}
