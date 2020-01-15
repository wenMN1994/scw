package com.dragon.scw.webui.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dragon.scw.vo.resp.AppResponse;
import com.dragon.scw.webui.service.TMemberServiceFeign;
import com.dragon.scw.webui.service.TProjectServiceFeign;
import com.dragon.scw.webui.vo.resp.ProjectVo;
import com.dragon.scw.webui.vo.resp.UserRespVo;

@Controller
public class DispatcherController {
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@RequestMapping(value= {"/","/index"})
	public String index(Model mode) {
		List<ProjectVo> data = (List<ProjectVo>) redisTemplate.opsForValue().get("projectInfo");
		
		if(data == null) {
			AppResponse<List<ProjectVo>> resp = projectServiceFeign.all();
		    data = resp.getData();
			
		    redisTemplate.opsForValue().set("projectInfo", data, 1, TimeUnit.HOURS);
		}
		
		mode.addAttribute("projectVoList", data);
		
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
		
		String preUrl = (String) session.getAttribute("preUrl");
		
		if(StringUtils.isEmpty(preUrl)) {
			return "redirect:/index";
		}else {
			return "redirect:"+preUrl;
		}
		
		
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
