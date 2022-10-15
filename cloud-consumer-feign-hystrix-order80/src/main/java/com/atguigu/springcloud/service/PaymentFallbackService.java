package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

	@Override
	public String paymentInfoOK(Integer id) {
		return "-----PaymentFallbackService fall back-paymentInfoOK , (┬＿┬)";
	}

	@Override
	public String paymentInfoTimeout(Integer id) {
		return "-----PaymentFallbackService fall paymentInfoTimeout , (┬＿┬)";
	}
}
