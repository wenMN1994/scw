package com.dragon.scw.project.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.scw.project.bean.TMember;
import com.dragon.scw.project.bean.TProject;
import com.dragon.scw.project.bean.TReturn;
import com.dragon.scw.project.service.ProjectInfoService;
import com.dragon.scw.project.service.UserServiceFeign;
import com.dragon.scw.project.vo.resp.ReturnPayConfirmVo;
import com.dragon.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;

/**
 * 
 * <p>Title: ProjectSupportController</p>  
 * <p>Description: 项目支持模块Controller</p>  
 * @author Dragon.Wen
 * @date 2020年1月13日
 */
@Api(tags="项目支持模块")
@RequestMapping("/project/support")
@RestController
public class ProjectSupportController {

	@Autowired
	ProjectInfoService projectInfoService;
	
	@Autowired
	UserServiceFeign userServiceFeign;
	
	@GetMapping("/returnConfirm/{projectId}/{retId}")
	public AppResponse<ReturnPayConfirmVo> confirmProjectReturnPayInfo(
	@PathVariable("projectId") Integer projectId,@PathVariable("retId") Integer retId) {
		// 1、项目的信息
		TProject project = projectInfoService.getProjectInfo(projectId);
		 
		// 2、项目发起人的信息
		Integer memberid = project.getMemberid();
		AppResponse<TMember> memberInfo = userServiceFeign.getMemberInfo(memberid);
		TMember member = memberInfo.getData();
		
		// 3、回报的信息
		TReturn returnInfo = projectInfoService.getProjectReturnById(retId);
		 
		// 4、封装数据并返回
		ReturnPayConfirmVo confirmVo = new ReturnPayConfirmVo();
		confirmVo.setFreight(returnInfo.getFreight());
		confirmVo.setMemberId(member.getId());
		confirmVo.setMemberName(member.getUsername());
		confirmVo.setNum(1);
		confirmVo.setPrice(returnInfo.getSupportmoney());
		confirmVo.setProjectId(project.getId());
		confirmVo.setProjectName(project.getName());
		confirmVo.setProjectRemark(project.getRemark());
		confirmVo.setReturnId(returnInfo.getId());
		confirmVo.setReturnName(returnInfo.getContent());
		confirmVo.setSignalpurchase(returnInfo.getSignalpurchase());
		 
		Integer totalMoney = returnInfo.getSupportmoney() * 1 + returnInfo.getFreight();
		BigDecimal bigDecimal = new BigDecimal(totalMoney.toString());
		confirmVo.setTotalPrice(bigDecimal);
		 
		return AppResponse.ok(confirmVo);
	}


}
