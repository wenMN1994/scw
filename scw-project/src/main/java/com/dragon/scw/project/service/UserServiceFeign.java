package com.dragon.scw.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dragon.scw.project.bean.TMember;
import com.dragon.scw.project.service.exp.handler.UserServiceFeignExceptionHandler;
import com.dragon.scw.vo.resp.AppResponse;

@FeignClient(value="SCW-USER", fallback=UserServiceFeignExceptionHandler.class)
public interface UserServiceFeign {
	
	@GetMapping("/user/info/{id}")
	public AppResponse<TMember> getMemberInfo(@PathVariable("id")Integer memberid); 
}
