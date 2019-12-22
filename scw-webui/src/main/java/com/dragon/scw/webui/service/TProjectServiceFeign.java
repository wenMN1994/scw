package com.dragon.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.exp.handler.TProjectServiceFeignExceptionHandler;
import com.dragon.scw.webui.vo.resp.ProjectVo;

/**
 * 
 * <p>Title: TProjectServiceFeign</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月21日
 */
@FeignClient(value="SCW-PROJECT", fallback=TProjectServiceFeignExceptionHandler.class)
public interface TProjectServiceFeign {

	@GetMapping("/project/all")
	public AppResponse<List<ProjectVo>> all();
}