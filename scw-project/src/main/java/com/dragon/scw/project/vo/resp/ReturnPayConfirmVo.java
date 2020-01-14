package com.dragon.scw.project.vo.resp;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * <p>Title: ReturnPayConfirmVo</p>  
 * <p>Description: 回报支付确认的vo</p>  
 * @author Dragon.Wen
 * @date 2020年1月12日
 */
@Data
public class ReturnPayConfirmVo implements Serializable {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// 项目信息
	private Integer projectId; 
	private String projectName;
	 private String projectRemark;

	// 发起人信息
	private Integer memberId;
	private String memberName;
	 
	// 回报的信息
	private Integer returnId;
	private String returnName;
	private String returnContent;
	private Integer num; // 支持数量，默认数量1，不能大于signalpurchase单笔限购数量
	private Integer price;// 支持单价
	private Integer freight; //运费
	private Integer signalpurchase;// 单笔限购数量
	 
	// 所有的double和float的运算在任何情况下都会导致丢失精度，使用BigDecimal进行计算
	private BigDecimal totalPrice;// 总价 totalPrice =price* num+ freight

}
