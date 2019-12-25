package com.dragon.scw.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/infoPage/{id}")
	public String projectInfoPage(@PathVariable("id") Integer id) {
		
		log.debug("项目ID={}",id);
		return "project/index";
	}
}
