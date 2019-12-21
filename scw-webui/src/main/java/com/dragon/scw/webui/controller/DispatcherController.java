package com.dragon.scw.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
