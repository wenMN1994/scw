package com.dragon.scw.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.dragon.scw.project.bean.TProject;
import com.dragon.scw.project.bean.TProjectImages;
import com.dragon.scw.project.bean.TProjectTag;
import com.dragon.scw.project.bean.TProjectType;
import com.dragon.scw.project.bean.TReturn;
import com.dragon.scw.project.constant.ProjectConstant;
import com.dragon.scw.project.mapper.TProjectImagesMapper;
import com.dragon.scw.project.mapper.TProjectMapper;
import com.dragon.scw.project.mapper.TProjectTagMapper;
import com.dragon.scw.project.mapper.TProjectTypeMapper;
import com.dragon.scw.project.mapper.TReturnMapper;
import com.dragon.scw.project.service.ProjectService;
import com.dragon.scw.project.vo.req.ProjectRedisStorageVo;
import com.dragon.scw.utils.AppDateUtils;

/**
 * 
 * <p>Title: ProjectServiceImpl</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月19日
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TProjectMapper projectMapper;
	
	@Autowired
	TProjectImagesMapper projectImagesMapper;
	
	@Autowired
	TReturnMapper returnMapper;
	
	@Autowired
	TProjectTypeMapper projectTypeMapper;
	
	@Autowired
	TProjectTagMapper projectTagMapper;
	
	@Transactional
	@Override
	public void saveProject(String accessToken, String projectToken, byte draft) {
		
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		
		//1.从redis中获取bigVo数据
		String bigStr = stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX+projectToken);
		
		ProjectRedisStorageVo bigVo = JSON.parseObject(bigStr, ProjectRedisStorageVo.class);
		//2.保存项目
		TProject project = new TProject();
		project.setName(bigVo.getName());
		project.setRemark(bigVo.getRemark());
		project.setMoney(bigVo.getMoney());
		project.setDay(bigVo.getDay());
		project.setStatus(draft+"");
		project.setMemberid(Integer.parseInt(memberId));
		project.setCreatedate(AppDateUtils.getFormatTime());
		//主键回填
		projectMapper.insertSelective(project);
		
		Integer projectId = project.getId();
		//3.保存图片
		TProjectImages projectImage = new TProjectImages();
		projectImage.setProjectid(projectId);
		projectImage.setImgurl(bigVo.getHeaderImage());
		projectImage.setImgtype((byte)0);
		projectImagesMapper.insertSelective(projectImage);
		
		List<String> detailsImage = bigVo.getDetailsImage();
		for (String imgPath : detailsImage) {
			TProjectImages pi = new TProjectImages();
			pi.setProjectid(projectId);
			pi.setImgurl(imgPath);
			pi.setImgtype((byte)1);
			projectImagesMapper.insertSelective(pi);
		}
		
		//4.保存回报
		List<TReturn> projectReturns = bigVo.getProjectReturns();
		for (TReturn retObj : projectReturns) {
			retObj.setProjectid(projectId);
			returnMapper.insertSelective(retObj);
		}
		
		//5.保存项目和分类关系
		List<Integer> typeIds = bigVo.getTypeids();
		for (Integer typeId : typeIds) {
			TProjectType pt = new TProjectType();
			pt.setProjectid(projectId);
			pt.setTypeid(typeId);
			projectTypeMapper.insertSelective(pt);
		}
		
		//6.保存项目和标签关系
		List<Integer> tagIds = bigVo.getTagids();
		for (Integer tagId : tagIds) {
			TProjectTag pt = new TProjectTag();
			pt.setProjectid(projectId);
			pt.setTagid(tagId);
			projectTagMapper.insertSelective(pt);
		}
		//7.保存发起人（省略）
		
		//8.保存法人（省略）
		
		//9.清理redis
		stringRedisTemplate.delete(ProjectConstant.TEMP_PROJECT_PREFIX+projectToken);
	}

}
