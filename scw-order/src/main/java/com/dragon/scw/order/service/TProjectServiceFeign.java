package com.dragon.scw.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dragon.scw.order.service.exp.handler.TProjectServiceFeignExceptionHandler;
import com.dragon.scw.order.vo.resp.TReturn;
import com.dragon.scw.vo.resp.AppResponse;

/**
 * 
 * <p>Title: TProjectServiceFeign</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月17日
 */
@FeignClient(value="SCW-PROJECT", fallback=TProjectServiceFeignExceptionHandler.class)
public interface TProjectServiceFeign {
	
	@GetMapping("/project/details/returns/info/{returnId}")
	public AppResponse<TReturn> returnInfo(@PathVariable("returnId") Integer returnId);
		
		
}
