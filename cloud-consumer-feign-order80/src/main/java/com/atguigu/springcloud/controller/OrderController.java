package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
	@Autowired
	private PaymentFeignService paymentFeignService;

	@PostMapping(value = "/consumer/payment/create")
	public CommonResult<Integer> create(@RequestBody Payment payment) {
		CommonResult<Integer> commonResult = this.paymentFeignService.create(payment);
		return commonResult;
	}

	@GetMapping(value = "/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		CommonResult<Payment> commonResult = this.paymentFeignService.getPaymentById(id);
		return commonResult;
	}

	@GetMapping(value = "/consumer/payment/timeout")
	public String paymentFeignTimeout() {
		return this.paymentFeignService.paymentFeignTimeout();
	}

}
