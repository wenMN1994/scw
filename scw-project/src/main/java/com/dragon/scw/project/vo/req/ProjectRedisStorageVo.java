package com.dragon.scw.project.vo.req;

import java.util.List;

import com.dragon.scw.project.bean.TReturn;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * <p>Title: ProjectRedisStorageVo</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月18日
 */
@Data
public class ProjectRedisStorageVo extends BaseVo {

	@ApiModelProperty("项目之前的临时token")
	private String projectToken;//项目的临时token
	
	private List<Integer> typeids; //项目的分类id 
    private List<Integer> tagids; //项目的标签id 
    
    private String name;//项目名称 
    private String remark;//项目简介 
    private Integer money;//筹资金额 
    private Integer day;//筹资天数 
    private String headerImage;//项目头部图片 
    private List<String> detailsImage;//项目详情图片 
    
    //发起人信息：自我介绍，详细自我介绍，联系电话，客服电话(后期实现)
    
    private List<TReturn> projectReturns;//项目回报 
    
    //确认信息，填写支付宝信息用于收款和身份核实（后期实现）
    
    
}
