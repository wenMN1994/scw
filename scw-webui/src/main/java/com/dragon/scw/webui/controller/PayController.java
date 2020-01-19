package com.dragon.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.config.AlipayConfig;
import com.dragon.scw.webui.service.TOrderServiceFeign;
import com.dragon.scw.webui.service.TProjectServiceFeign;
import com.dragon.scw.webui.vo.resp.OrderFormInfoSubmitVo;
import com.dragon.scw.webui.vo.resp.OrderInfoSubmitVo;
import com.dragon.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.dragon.scw.webui.vo.resp.TOrder;
import com.dragon.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>Title: PayController</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月16日
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class PayController {
	
	@Autowired
	TOrderServiceFeign orderServiceFeign;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@ResponseBody
	@PostMapping(value = "/pay", produces = "text/html")
	public String pay(OrderFormInfoSubmitVo vo, HttpSession session, Model model) {
		log.debug("提交订单-立即支付=OrderFormInfoSubmitVo={}",vo);
		
		//1.保存订单
		UserRespVo loginMember = (UserRespVo) session.getAttribute("loginMember");
		if(loginMember == null) {
			//session.setAttribute("preUrl", "/order/pay/");
			return "redirect:/login";
		}
		
		OrderInfoSubmitVo feignVo = new OrderInfoSubmitVo ();
		BeanUtils.copyProperties(vo, feignVo);
		log.debug("登录信息loginMember={}",loginMember.getAccessToken());
		feignVo.setAccessToken(loginMember.getAccessToken());
		
		ReturnPayConfirmVo attribute = (ReturnPayConfirmVo) session.getAttribute("returnConfirm");
		
		if(attribute == null) {
			//session.setAttribute("preUrl", "/order/pay/");
			return "redirect:/login";
		}
		
		feignVo.setProjectid(attribute.getProjectId());
		feignVo.setReturnid(attribute.getReturnId());
		feignVo.setRtncount(attribute.getNum());

		AppResponse<TOrder> createOrder = orderServiceFeign.createOrder(feignVo);
		 
		TOrder data = createOrder.getData();
		log.debug("订单详情：{}", data);

		//2.支付
		String orderName = attribute.getProjectName();
		
		String result = payOrder(data.getOrdernum(), data.getMoney(), 
				orderName, feignVo.getRemark()); 
 
		return result;//这是一个表单，返回给浏览器，并立即提交表单，出来二维码支付页面。
	}

	private String payOrder(String out_trade_no, Integer total_amount, String subject, String body) {
		try {
			// 1、创建支付宝客户端
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
			AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
			AlipayConfig.sign_type);
			 
			// 2、创建一次支付请求
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

			// 3、构造支付请求数据
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
					+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

			// 4、请求
			 String result = alipayClient.pageExecute(alipayRequest).getBody();

			return result;// 支付跳转页的代码，一个form表单，来到扫码页
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
}
