package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
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
	@HystrixCommand(fallbackMethod = "paymentTimeoutFallBack",
			commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
			})
	public String paymentTimeout(Integer id) {
		//int timeNumber = 3; //3<5 正常
		int timeNumber = 10; //6>5 返回托底数据 实现了降级
		//int n = 10 / 0; //出现异常了，直接执行兜底参数方法
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "线程池：" + Thread.currentThread().getName() + "   paymentInfoTimeout,id：" + id + "\t" + "呜呜呜" + " 耗时(秒)" + timeNumber;
	}

	//兜底参数用来实现降级
	public String paymentTimeoutFallBack(Integer id) {
		return "线程池：" + Thread.currentThread().getName() + " paymentTimeoutFallBack,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
	}

	//服务熔断
	@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
			commandProperties = {
					@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //10个请求
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠窗口期10秒
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //默认失败率50%
			})
	public String paymentCircuitBreaker(Integer id) {
		if (id < 0) {
			throw new RuntimeException("*****id 不能负数");
		}
		String serialNumber = IdUtil.simpleUUID();//hutool.cn工具包

		return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serialNumber;
	}

	public String paymentCircuitBreaker_fallback(Integer id) {
		return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " + id;
	}
}