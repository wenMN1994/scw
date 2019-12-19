package com.dragon.scw.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.scw.user.bean.UserTest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>Title: HelloController</p>  
 * <p>Description: 测试</p>  
 * @author Dragon.Wen
 * @date 2019年12月16日
 */
@Api(tags="Swagger测试")
@RestController
public class HelloController {
	
	@ApiOperation(value="hello方法测试")
	@ApiImplicitParams(value= {
			@ApiImplicitParam(name="name", value="姓名", required=true),
			@ApiImplicitParam(name="age", value="年龄")
	})
	@GetMapping("/hello")
	public String hello(String name, String age) {
		return "OK:"+name;
	}
	
	@ApiOperation("测试方法Hello2")
	@ApiImplicitParams(value= {
			@ApiImplicitParam(name="name",value="姓名",required=true),
			@ApiImplicitParam(name="email",value="电子邮件")
	})
	@PostMapping("/user")
	public UserTest user(String name ,String email) {
		UserTest user = new UserTest();
		user.setName(name);
		user.setEmail(email);
		return user;
	}


}
