package com.dragon.scw.project.service.exp.handler;

import org.springframework.stereotype.Component;

import com.dragon.scw.project.bean.TMember;
import com.dragon.scw.project.service.UserServiceFeign;
import com.dragon.scw.vo.resp.AppResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>Title: UserServiceFeignExceptionHandler</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月13日
 */
@Slf4j
@Component
public class UserServiceFeignExceptionHandler implements UserServiceFeign {
	
	@Override
	public AppResponse<TMember> getMemberInfo(Integer memberid) {
		AppResponse<TMember> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询会员信息】失败");
		log.error("调用远程服务【查询会员信息】失败");
		return resp;
	}

}
