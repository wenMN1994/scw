package com.dragon.scw.webui.service.exp.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TProjectServiceFeign;
import com.dragon.scw.webui.vo.resp.ProjectVo;
import com.dragon.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>Title: TProjectServiceFeignExceptionHandler</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月21日
 */
@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

	@Override
	public AppResponse<List<ProjectVo>> all() {
		AppResponse<List<ProjectVo>> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询首页热点项目】失败");
		log.error("调用远程服务【查询首页热点项目】失败");
		return resp;
	}
	
}
