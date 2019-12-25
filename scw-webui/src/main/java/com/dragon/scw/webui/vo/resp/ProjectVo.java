package com.dragon.scw.webui.vo.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProjectVo implements Serializable {
	
	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;// 项目id
	
	private Integer memberid;// 会员id
	
	//第二步：收集项目基本信息以及发起人信息（暂时不做）
	
	private String name;// 项目名称
	private String remark;// 项目简介
	
	private String headerImage;// 项目头部图片
	private List<String> detailsImage = new ArrayList<>();// 项目详情图片
	// 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
	
	//第三步：收集回报信息
	private List<TReturn> projectReturns = new ArrayList<TReturn>();// 项目回报
}
