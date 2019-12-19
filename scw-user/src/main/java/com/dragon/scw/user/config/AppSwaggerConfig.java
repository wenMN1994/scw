package com.dragon.scw.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * <p>
 * Title: AppSwaggerConfig
 * </p>
 * <p>
 * Description: Swagger2配置类
 * </p>
 * 
 * @author Dragon.Wen
 * @date 2019年12月18日
 */
@EnableSwagger2 // 开启swagger2自动生成api文档的功能
@Configuration
public class AppSwaggerConfig {
	@Value("${swagger2.enable:false}")
	private boolean enable = false;

	@Bean("用户模块")
	public Docket projectApis() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("用户模块").select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.regex("/user.*"))
				.build().apiInfo(apiInfo()).enable(enable);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("尚筹网-系统平台接口文档").description("提供会员管理")
				.termsOfServiceUrl("http://www.dragonwen.cn/").version("1.0").build();
	}
}
