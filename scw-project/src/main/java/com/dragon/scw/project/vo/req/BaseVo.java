package com.dragon.scw.project.vo.req;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * <p>Title: BaseVo</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月18日
 */
@Data
public class BaseVo implements Serializable {
	
	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String accessToken;
}
