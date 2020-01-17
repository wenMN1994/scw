package com.dragon.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dragon.scw.vo.resp.AppResponse;
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
	
	@PostMapping(value = "/pay", produces = "text/html")
	public String pay(OrderFormInfoSubmitVo vo, HttpSession session, Model model) {
		log.debug("提交订单-立即支付=OrderFormInfoSubmitVo={}",vo);
		
		UserRespVo loginMember = (UserRespVo) session.getAttribute("loginMember");
		if(loginMember == null) {
			//session.setAttribute("preUrl", "/order/pay/");
			return "redirect:/login";
		}
		
		// 1、生成订单信息
		OrderInfoSubmitVo feignVo = new OrderInfoSubmitVo ();
		BeanUtils.copyProperties(vo, feignVo);
		log.debug("登录信息loginMember={}",loginMember.getAccessToken());
		feignVo.setAccessToken(loginMember.getAccessToken());
//		feignVo.setAddress(vo.getAddress());
//		feignVo.setInvoice(vo.getInvoice());
//		feignVo.setInvoictitle(vo.getInvoictitle());
//		feignVo.setRemark(vo.getRemark());
		
		// 2、获取之前保存的项目id，回报id等信息
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

		//AppResponse<ProjectDetailVo> detailsInfo = projectServiceFeign.detailsInfo(data.getProjectid());

		return "redirect:/member/minecrowdfunding";
	}

}
