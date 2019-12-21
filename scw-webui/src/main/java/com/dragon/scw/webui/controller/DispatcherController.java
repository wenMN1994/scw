package com.dragon.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TMemberServiceFeign;
import com.dragon.scw.webui.vo.resp.UserRespVo;

@Controller
public class DispatcherController {
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct, String userpswd, HttpSession session) {
		AppResponse<UserRespVo> resp = memberServiceFeign.login(loginacct, userpswd);
		
		UserRespVo data = resp.getData();
		
		if(data == null) {
			return "login";
		}
		
		session.setAttribute("loginMember", data);
		
		return "redirect:/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session!=null) {
			session.removeAttribute("loginMember");
			session.invalidate();
		}
		return "redirect:/index";
	}
}
