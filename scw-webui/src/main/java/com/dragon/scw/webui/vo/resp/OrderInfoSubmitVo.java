package com.dragon.scw.webui.vo.resp;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * <p>Title: OrderInfoSubmitVo</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月15日
 */
@Data
public class OrderInfoSubmitVo implements Serializable {/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String accessToken; 
	private Integer projectid; 
	private Integer returnid; 
	private Integer rtncount; 

	private String address; 
	private Byte invoice; 
	private String invoictitle; 
	private String remark;

}
