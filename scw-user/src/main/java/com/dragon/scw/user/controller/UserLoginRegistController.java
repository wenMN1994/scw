package com.dragon.scw.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.scw.user.component.SmsTemplate;
import com.dragon.scw.user.service.TMemberService;
import com.dragon.scw.user.vo.req.UserRegistVo;
import com.dragon.scw.user.vo.req.UserRespVo;
import com.dragon.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@RestController
@Slf4j
public class UserLoginRegistController {
	
	@Autowired
	SmsTemplate smsTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TMemberService memberService;
	
	@ApiOperation(value="用户登陆") 
	@ApiImplicitParams(value={
			@ApiImplicitParam(value="登陆账号",name="loginacct"),
			@ApiImplicitParam(value="用户密码",name="password")
	})
	@PostMapping("/login")
	public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct, @RequestParam("password") String password){
		try {
			UserRespVo vo = memberService.getUserByLogin(loginacct, password);
			log.debug("登陆成功-{}",loginacct);
			return AppResponse.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("登陆失败-{}-{}",loginacct, e.getMessage());
			AppResponse<UserRespVo> resp = AppResponse.fail(null);
			resp.setMsg(e.getMessage());
			return resp;
		}
	} 
	
	@ApiOperation(value="用户注册") 
	@PostMapping("/register")
	public AppResponse<Object> register(UserRegistVo vo){
		String loginacct = vo.getLoginacct();
		if(!StringUtils.isEmpty(loginacct)){
			String code = stringRedisTemplate.opsForValue().get(loginacct);
			if(!StringUtils.isEmpty(code)) {
				if(code.equals(vo.getCode())) {
					
					//需要校验账号是否唯一
					//需要校验email地址是否被占用

					//保存数据
					int i = memberService.saveTMember(vo);
					
					if(i == 1) {
						stringRedisTemplate.delete(loginacct);
						return AppResponse.ok("ok");
					} else {
						return AppResponse.fail(null);
					}
				} else {
					AppResponse<Object> resp = AppResponse.fail(null);
					log.debug("验证码错误，请重新输入！");
					resp.setMsg("验证码错误，请重新输入！");
					return resp;
				}
			} else {
				AppResponse<Object> resp = AppResponse.fail(null);
				log.debug("验证码已失效，请重新发送！");
				resp.setMsg("验证码已失效，请重新发送！");
				return resp;
			}
		} else {
			AppResponse<Object> resp = AppResponse.fail(null);
			log.debug("用户名不能为空");
			resp.setMsg("用户名不能为空");
			return resp;
		}
		
	} 
	
	@ApiOperation(value="发送短信验证码") 
	@PostMapping("/sendsms")
	public AppResponse<Object> sendsms(String loginacct){
		//1、生成验证码保存到服务器，准备用户提交上来进行对比
		StringBuilder code = new StringBuilder();
		for(int i = 1; i <= 4; i++) {
			code.append(new Random().nextInt(10));
		}

		//2、保存验证码和手机号的对应关系
		stringRedisTemplate.opsForValue().set(loginacct, code.toString(), 5, TimeUnit.MINUTES);
		
		//2、短信发送构造参数
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", loginacct);
		querys.put("param", "code:"+code);
		querys.put("tpl_id", "TP19121716");//短信模板
		//3、发送短信
		String sendCode = smsTemplate.sendCode(querys);
		if(sendCode.equals("")||sendCode.equals("fail")){
		//短信失败
		log.debug("短信发送失败");
		return AppResponse.fail("短信发送失败");
		}

		log.debug("短信发送成功-验证码：{}",code.toString());
		
		return AppResponse.ok("ok");
	} 
	
	@ApiOperation(value="验证短信验证码") 
	@PostMapping("/valide")
	public AppResponse<Object> valide(){
		return AppResponse.ok("ok");
	} 
	
	@ApiOperation(value="重置密码") 
	@PostMapping("/reset")
	public AppResponse<Object> reset(){
		return AppResponse.ok("ok");
	} 
	
	

}
