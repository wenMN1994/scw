package com.dragon.scw.webui.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TMemberServiceFeign;
import com.dragon.scw.webui.service.TProjectServiceFeign;
import com.dragon.scw.webui.vo.resp.ProjectDetailVo;
import com.dragon.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.dragon.scw.webui.vo.resp.TMemberAddress;
import com.dragon.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>Title: ProjectController</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月25日
 */
@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	TProjectServiceFeign projectServiceFeign; 
	
	@Autowired
	TMemberServiceFeign userServiceFeign;
	
	@RequestMapping("/infoPage/{id}")
	public String projectInfoPage(@PathVariable("id") Integer id, Model model) {
		AppResponse<ProjectDetailVo> response = projectServiceFeign.detailsInfo(id);
		ProjectDetailVo data = response.getData();
		model.addAttribute("projectDetails", data);
		log.debug("商品详情信息={}",data);
		return "project/index";
	
	}
	
	/**
	 * 
	 * <p>Title: toReturnConfirmPage</p>  
	 * <p>Description: 支持购买某个档位；去回报确认页</p>  
	 * @param projectId
	 * @param retId
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/return/confirm")
	public String toReturnConfirmPage(@RequestParam("projectId") Integer projectId,
	@RequestParam("retId") Integer retId, Model model, HttpSession session) { 
		
		AppResponse<ReturnPayConfirmVo> vo = projectServiceFeign.confirmProjectReturnPayInfo(projectId, retId);
		ReturnPayConfirmVo data = vo.getData();
		model.addAttribute("confirmReturn", data); 
		session.setAttribute("returnConfirm", data);//结算页也需要这个数据,采用session共享数据

		return "project/pay-step-1";
	}
	
	@GetMapping("/pay/confirm/{num}")
	public String payConfirmPage(HttpSession session, @PathVariable("num") Integer num, Model model) {
		
		// 1、确认当前用户是否已经登录
		UserRespVo loginMember = (UserRespVo) session.getAttribute("loginMember");
		if(loginMember == null) {
			session.setAttribute("preUrl", "/project/pay/confirm/"+num);
			// 2、如果没有登录就去登录页
			return "redirect:/login";
		}
		
		ReturnPayConfirmVo vo = (ReturnPayConfirmVo) session.getAttribute("returnConfirm");
		vo.setNum(num);
		Integer supportTotalPrice = vo.getPrice() * num;
		vo.setSupportTotalPrice(new BigDecimal(supportTotalPrice.toString()));
		Integer totalPrice = vo.getPrice() * num + vo.getFreight();
		vo.setTotalPrice(new BigDecimal(totalPrice.toString()));
		// 每一步更新redis数据
		session.setAttribute("returnConfirm", vo);
		 
		// 查询用户的收货地址
		AppResponse<List<TMemberAddress>> addresses =
		     userServiceFeign.addresses(loginMember.getAccessToken());
		List<TMemberAddress> list = addresses.getData();
		model.addAttribute("addresses", list);
		return "project/pay-step-2";

	}
}
