package com.dragon.scw.webui.vo.resp;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * <p>Title: OrderFormInfoSubmitVo</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月15日
 */
@Data
public class OrderFormInfoSubmitVo implements Serializable {
	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String address;//收货地址id
	private Byte invoice;//0代表不要  1-代表要
	private String invoictitle;//发票抬头
	private String remark;//订单的备注

}
