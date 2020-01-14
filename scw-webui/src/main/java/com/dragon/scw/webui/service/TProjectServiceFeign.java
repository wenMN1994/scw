package com.dragon.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.exp.handler.TProjectServiceFeignExceptionHandler;
import com.dragon.scw.webui.vo.resp.ProjectDetailVo;
import com.dragon.scw.webui.vo.resp.ProjectVo;
import com.dragon.scw.webui.vo.resp.ReturnPayConfirmVo;

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

	@GetMapping("/project/details/info/{projectId}")
	public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId);

	@GetMapping("/project/support/returnConfirm/{projectId}/{retId}")
	public AppResponse<ReturnPayConfirmVo> confirmProjectReturnPayInfo(@PathVariable("projectId") Integer projectId, @PathVariable("retId") Integer retId);
}
