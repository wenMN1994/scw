package com.dragon.scw.user.vo.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * <p>Title: UserRegistVo</p>  
 * <p>Description: 用户注册提交的数据VO</p>  
 * @author Dragon.Wen
 * @date 2019年12月17日
 */
@ApiModel
@Data
public class UserRegistVo implements Serializable {
	
	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("手机号")
	private String loginacct;
	
	@ApiModelProperty("用户名称")
	private String username;
	
	@ApiModelProperty("密码")
	private String userpswd;
	
	@ApiModelProperty("邮箱")
	private String email;
	
	@ApiModelProperty("验证码")
	private String code;
	
	@ApiModelProperty("用户类型: 0 - 个人， 1 - 企业")
	private String usertype;

}
