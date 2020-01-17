package com.dragon.scw.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * <p>Title: TMemberController</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月17日
 */
@Controller
@RequestMapping("/member")
public class TMemberController {

	@RequestMapping("/minecrowdfunding")
	public String memberOrderList() {
		return "/member/minecrowdfunding";
	}
}
