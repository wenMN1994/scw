package com.dragon.scw.webui.vo.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * <p>Title: ProjectDetailVo</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月26日
 */
@ToString
@Data
public class ProjectDetailVo implements Serializable {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 项目id
	
	private String name;// 项目名称
	
	private String remark;// 项目简介
	
	private Long money;//筹资金额

    private Integer day;//筹资天数

    private String status;//筹资状态
    
    private Long supportmoney = 0L;//支持金额

    private Integer supporter = 0;//支持者数量

    private Integer completion = 0;//完成度
    
    private Integer follower = 100;//关注者数量
	
	private String headerImage;// 项目头部图片
	private List<String> detailsImage = new ArrayList<>();// 项目详情图片
	// 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
	
	//第三步：收集回报信息
	private List<TReturn> projectReturns = new ArrayList<TReturn>();// 项目回报
}
