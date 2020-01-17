package com.dragon.scw.order.service.exp.handler;

import org.springframework.stereotype.Component;

import com.dragon.scw.order.service.TProjectServiceFeign;
import com.dragon.scw.order.vo.resp.TReturn;
import com.dragon.scw.vo.resp.AppResponse;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * <p>Title: TProjectServiceFeignExceptionHandler</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月17日
 */
@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

	@Override
	public AppResponse<TReturn> returnInfo(Integer returnId) {
		AppResponse<TReturn> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询项目回报信息】失败");
		log.error("调用远程服务【查询项目回报信息】失败");
		return resp;
	}

}
