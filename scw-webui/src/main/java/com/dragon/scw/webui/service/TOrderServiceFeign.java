package com.dragon.scw.webui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.exp.handler.TOrderServiceFeignExceptionHandler;
import com.dragon.scw.webui.vo.resp.OrderInfoSubmitVo;
import com.dragon.scw.webui.vo.resp.TOrder;

@FeignClient(value="SCW-ORDER", fallback=TOrderServiceFeignExceptionHandler.class)
public interface TOrderServiceFeign {

	@PostMapping("/order/create")
	AppResponse<TOrder> createOrder(@RequestBody OrderInfoSubmitVo feignVo);

}
