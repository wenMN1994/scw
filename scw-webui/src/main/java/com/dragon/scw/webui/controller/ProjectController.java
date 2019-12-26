package com.dragon.scw.webui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TProjectServiceFeign;
import com.dragon.scw.webui.vo.resp.ProjectDetailVo;

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
}
