package com.dragon.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TProjectServiceFeign;
import com.dragon.scw.webui.vo.resp.ProjectDetailVo;
import com.dragon.scw.webui.vo.resp.ReturnPayConfirmVo;

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
	
	@GetMapping("/pay/confirm")
	public String payConfirmPage(HttpSession session, @RequestParam("num") Integer num, Model model) {
		
		// 1、确认当前用户是否已经登录
		
		// 2、如果没有登录就去登录页
		
		return "project/pay-step-2";
	}
}
