package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "CLOUD-HYSTRIX-PAYMENT-SERVICE", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfoOK(@PathVariable("id") Integer id);

	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeout(@PathVariable("id") Integer id);
}
