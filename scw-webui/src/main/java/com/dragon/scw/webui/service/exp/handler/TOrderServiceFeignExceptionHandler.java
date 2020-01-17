package com.dragon.scw.webui.service.exp.handler;

import org.springframework.stereotype.Component;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TOrderServiceFeign;
import com.dragon.scw.webui.vo.resp.OrderInfoSubmitVo;
import com.dragon.scw.webui.vo.resp.TOrder;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * <p>Title: TOrderServiceFeignExceptionHandler</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月16日
 */
@Slf4j
@Component
public class TOrderServiceFeignExceptionHandler implements TOrderServiceFeign {
	
	@Override
	public AppResponse<TOrder> createOrder(OrderInfoSubmitVo feignVo) {
		AppResponse<TOrder> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【订单创建】失败");
		log.error("调用远程服务【订单创建】失败");
		return resp;
	}

}
