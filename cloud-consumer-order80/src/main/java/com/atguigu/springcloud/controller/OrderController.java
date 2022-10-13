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
	@Autowired
	//SpringBoot启动时并没有创建RestTemplate对象，此时运行出现NullPointerException
	private RestTemplate restTemplate;

	@PostMapping(value = "/consumer/payment/create")
	public CommonResult<Integer> create(@RequestBody Payment payment) {
		CommonResult commonResult = restTemplate.postForObject("http://localhost:8001/payment/create", payment, CommonResult.class);
		System.out.println("添加数据成功");
		return commonResult;
	}

	@GetMapping(value = "/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		CommonResult commonResult = restTemplate.getForObject("http://localhost:8001/payment/get/" + id, CommonResult.class);
		System.out.println("查询成功");
		return commonResult;
	}
}