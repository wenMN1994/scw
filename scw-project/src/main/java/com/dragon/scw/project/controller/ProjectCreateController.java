package com.dragon.scw.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dragon.scw.project.component.OssTemplate;
import com.dragon.scw.project.constant.ProjectConstant;
import com.dragon.scw.project.vo.req.BaseVo;
import com.dragon.scw.project.vo.req.ProjectRedisStorageVo;
import com.dragon.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
public class ProjectCreateController {

	@Autowired
	OssTemplate ossTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@ApiOperation(value = "项目初始创建")
	@PostMapping("/init")
	public AppResponse<Object> init(BaseVo vo) {
		
		try {
			String accessToken = vo.getAccessToken();
			
			if(StringUtils.isEmpty(accessToken)) {
				AppResponse<Object> resp = AppResponse.fail(null);
				resp.setMsg("请求必须提供AccessToken值");
				return resp;
			}
			
			String memberId = stringRedisTemplate.opsForValue().get(accessToken);
			
			if(StringUtils.isEmpty(memberId)) {
				AppResponse<Object> resp = AppResponse.fail(null);
				resp.setMsg("请先登录系统，再发布项目！");
				return resp;
			}
			
			ProjectRedisStorageVo bigVo = new ProjectRedisStorageVo();
			
			BeanUtils.copyProperties(vo, bigVo);
			
			String projectToken = UUID.randomUUID().toString().replaceAll("-", "");
			bigVo.setProjectToken(projectToken);
			
			String bigStr = JSON.toJSONString(bigVo);
			
			stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+projectToken, bigStr);
			
			return AppResponse.ok("ok");
		} catch (BeansException e) {
			e.printStackTrace();
			log.error("项目初始创建失败-{}", e.getMessage());
			return AppResponse.fail(null);
		}
	}
	
	@ApiOperation(value="项目基本信息")
	@PostMapping("/baseinfo")
	public AppResponse<Object> baseinfo(){
		
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "添加项目回报档位")
	@PostMapping("/return")
	public AppResponse<Object> returnDetail() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "删除项目回报档位")
	@DeleteMapping("/return")
	public AppResponse<Object> deleteReturnDetail() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "上传图片")
	@PostMapping("/upload")
	public AppResponse<Object> upload(@RequestParam("uploadfile") MultipartFile[] files) {
		
		try {
			List<String> filePathList = new ArrayList<String>();
			
			for (MultipartFile multipartFile : files) {
				String filePath = ossTemplate.upload(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
				filePathList.add(filePath);
			}
			log.debug("上传文件路径-{}", filePathList);
			return AppResponse.ok(filePathList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("上传文件出现异常");;
			return AppResponse.fail(null);
		}
	}
	
	@ApiOperation(value = "确认项目法人信息")
	@PostMapping("/confirm/legal")
	public AppResponse<Object> legal() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "项目草稿保存")
	@PostMapping("/tempsave")
	public AppResponse<Object> tempsave() {
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value = "项目提交审核申请")
	@PostMapping("/submit")
	public AppResponse<Object> submit() {
		return AppResponse.ok("ok");
	}
	
}
