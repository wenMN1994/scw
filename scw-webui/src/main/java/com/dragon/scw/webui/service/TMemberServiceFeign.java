package com.dragon.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.exp.handler.TMemberServiceFeignExceptionHandler;
import com.dragon.scw.webui.vo.resp.TMemberAddress;
import com.dragon.scw.webui.vo.resp.UserRespVo;

/**
 * 
 * <p>Title: TMemberServiceFeign</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月21日
 */
@FeignClient(value="SCW-USER",fallback=TMemberServiceFeignExceptionHandler.class)
public interface TMemberServiceFeign {
	
	@PostMapping("/user/login")
	public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password);

	@GetMapping("/user/address/{accessToken}")
	public AppResponse<List<TMemberAddress>> addresses(@RequestParam("accessToken")String token);

}
