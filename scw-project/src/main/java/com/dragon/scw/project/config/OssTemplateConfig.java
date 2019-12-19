package com.dragon.scw.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dragon.scw.project.component.OssTemplate;

/**
 * 
 * <p>Title: OssTemplateConfig</p>  
 * <p>Description: OSS云存储服务的模板对象配置类</p>  
 * @author Dragon.Wen
 * @date 2019年12月18日
 */
@Configuration
public class OssTemplateConfig {

	@ConfigurationProperties(prefix="oss")
	@Bean
	public OssTemplate ossTemplate() {
		return new OssTemplate();
	}
	
}
