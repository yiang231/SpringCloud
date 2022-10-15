package com.atguigu.springmvc.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyAuthGateWayFilter implements GlobalFilter, Ordered {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//判断请求中是否有某个请求参数，如果有放行，如果没有，返回错误的响应
		//http://127.0.0.1:9527/payment/circuit/13?username=zhangsan
		String username = (String) exchange.getRequest().getQueryParams().getFirst("username");
		if (StringUtils.isEmpty(username)) {
			exchange.getResponse().setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);//203
			return exchange.getResponse().setComplete();
		}
		//放行
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0; //过滤器的索引，数越小越早执行
	}
}
