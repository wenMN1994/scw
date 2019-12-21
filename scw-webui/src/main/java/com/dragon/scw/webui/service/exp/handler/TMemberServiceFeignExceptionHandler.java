package com.dragon.scw.webui.service.exp.handler;

import org.springframework.stereotype.Component;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TMemberServiceFeign;
import com.dragon.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * <p>Title: TMemberServiceFeignExceptionHandler</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月21日
 */
@Slf4j
@Component
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign {

	@Override
	public AppResponse<UserRespVo> login(String loginacct, String password) {
		AppResponse<UserRespVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【登录】失败");
		log.error("调用远程服务【登录】失败");
		return resp;
	}

}
