package com.atguigu.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
SpringBoot整合MyBatis
1.pom.xml添加启动器
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
2.application.yml中进行配置
    #整合MyBatis，和整合tk_mybatis很相似
    #不同之处是tk_mybatis不需要映射文件
    mybatis:
      mapper-locations: classpath:/mapper/*.xml
      type-aliases-package: com.atguigu.springcloud.entity
      configuration:
        map-underscore-to-camel-case: true
3. 进行组件扫描
    方法1：启动类中添加@MapperScan(basePackages = "com.atguigu.springcloud.dao")
    方法2：在DAO接口上添加@Mapper注解
    推荐使用方法1，只需要写一次，使用方法2需要每个接口上都写@Mapper注解
    @MapperScan：org.mybatis.spring.annotation.MapperScan;  mybatis-spring整合插件
    @Mapper org.apache.ibatis.annotations.Mapper; MyBatis提供的
 */
@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.springcloud.dao")
public class PaymentMain8001 {
	public static void main(String[] args) {
		SpringApplication.run(PaymentMain8001.class, args);
	}
}
