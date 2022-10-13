package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
	//成功
	public String paymentInfoOK(Integer id) {
		return "线程池：" + Thread.currentThread().getName() + "   paymentInfoOK,id：  " + id + "\t" + "哈哈哈";
	}

	//失败
	@HystrixCommand(fallbackMethod = "paymentTimeoutFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
	})
	public String paymentTimeout(Integer id) {
		int timeNumber = 3; //3<5 正常
		//int timeNumber = 13; //13>5 返回托底数据 实现了降级
		//int n = 10/0; //出现异常了
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "线程池：" + Thread.currentThread().getName() + "   paymentInfoTimeout,id：  " + id + "\t" + "呜呜呜" + " 耗时(秒)" + timeNumber;
	}

	public String paymentTimeoutFallBack(Integer id) {
		return "线程池：" + Thread.currentThread().getName() + " paymentTimeoutFallBack,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
	}
}