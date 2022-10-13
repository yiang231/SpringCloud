package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class FeignConfig {
	@Bean
	public Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
