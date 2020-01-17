package com.dragon.scw.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.scw.order.bean.TOrder;
import com.dragon.scw.order.service.TOrderService;
import com.dragon.scw.order.vo.req.OrderInfoSubmitVo;
import com.dragon.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>Title: OrderController</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月16日
 */
@Slf4j
@Api(tags = "订单模块")
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	TOrderService orderService;
	
	@ApiOperation("创建订单，会返回订单的所有信息")
	@PostMapping("/create")
	public AppResponse<TOrder> createOrder(@RequestBody OrderInfoSubmitVo vo) {
		
		try {
			log.debug("保存订单开始={}", vo);
			
			TOrder order = orderService.createOrder(vo);
			
			AppResponse<TOrder> resp = AppResponse.ok(order);
			
			log.debug("订单保存成功");
			return resp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppResponse<TOrder> resp = AppResponse.fail(null);
			resp.setMsg("保存订单失败");
			log.error("保存订单失败");
			return resp;
		}
	}

}
