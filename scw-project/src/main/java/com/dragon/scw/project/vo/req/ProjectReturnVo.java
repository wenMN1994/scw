package com.dragon.scw.project.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 发布项目时，用于封装项目回报的VO类
 * @author Administrator
 *
 */
@ApiModel
@Data
public class ProjectReturnVo extends BaseVo {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 3606021650966946437L;

	@ApiModelProperty("项目的临时token")
	private String projectToken;// 项目的临时token
	 
	@ApiModelProperty(value = "回报类型：  0-虚拟回报   1-实物回报", required = true)
	private String type;
	 
	@ApiModelProperty(value = "支持金额", required = true)
	private Integer supportmoney;
	 
	@ApiModelProperty(value = "回报内容", required = true)
	private String content;
	 
	@ApiModelProperty(value = "回报数量", required = true)
	private Integer count;
	 
	@ApiModelProperty(value = "单笔限购", required = true)
	private Integer signalpurchase;
	 
	@ApiModelProperty(value = "限购数量", required = true)
	private Integer purchase;
	 
	@ApiModelProperty(value = "运费", required = true)
	private Integer freight;
	 
	@ApiModelProperty(value = "发票  0-不开发票  1-开发票", required = true)
	private String invoice;
	 
	@ApiModelProperty(value = "回报时间，天数", required = true)
	private Integer rtndate;
	
}
