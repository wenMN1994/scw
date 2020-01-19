package com.dragon.scw.order.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
 * <p>
 * Title: OrderController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
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


	/**
	 * 
	 * <p>Title: payAsync</p>  
	 * <p>Description: 异步通知的处理</p>  
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("/payAsync")
	public String payAsync(HttpServletRequest request) throws UnsupportedEncodingException {
		log.debug("支付宝支付异步通知完成....");
		// 修改订单的状态
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝流水号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		
		if (trade_status.equals("TRADE_FINISHED")) {
			log.debug("订单【{}】,已经完成...不能再退款。数据库都改了", out_trade_no);
		} else if (trade_status.equals("TRADE_SUCCESS")) {
			log.debug("订单【{}】,已经支付成功...可以退款。数据库都改了", out_trade_no);
		}
		
		// 改订单状态
		orderService.updateOrderStatus(out_trade_no);
		
		// 支付宝收到了success说明处理完成，不会再通知
		return "success";
	}

}
