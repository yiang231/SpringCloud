package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {
	@Bean
	@LoadBalanced//采用默认的策略轮询
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
