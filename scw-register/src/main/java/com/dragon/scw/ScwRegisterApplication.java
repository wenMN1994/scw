package com.dragon.scw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 
 * <p>Title: ScwRegisterApplication</p>  
 * <p>Description: 注册中心启动程序</p>  
 * @author Dragon.Wen
 * @date 2019年12月15日
 */
@EnableEurekaServer
@SpringBootApplication
public class ScwRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScwRegisterApplication.class, args);
	}

}
