package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
/*要使用的微服务名称（支付）*/
@FeignClient(name = "CLOUD-PAYMENT-SERVICE") //!!!!
public interface PaymentFeignService {

	@PostMapping(value = "/payment/create")
	public CommonResult<Integer> create(@RequestBody Payment payment);


	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
