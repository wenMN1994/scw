package com.dragon.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * <p>Title: ScwUserApplication</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月15日
 */
@EnableTransactionManagement
@EnableDiscoveryClient  //开启服务注册发现 功能
@MapperScan("com.dragon.scw.user.mapper")
@SpringBootApplication
public class ScwUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScwUserApplication.class, args);
	}

}
