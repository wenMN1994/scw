package com.dragon.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient  //开启服务注册发现 功能
@MapperScan("com.dragon.scw.project.mapper")
@SpringBootApplication
public class ScwProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScwProjectApplication.class, args);
	}

}
