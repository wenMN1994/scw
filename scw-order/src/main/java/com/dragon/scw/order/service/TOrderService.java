package com.dragon.scw.order.service;

import com.dragon.scw.order.bean.TOrder;
import com.dragon.scw.order.vo.req.OrderInfoSubmitVo;

/**
 * 
 * <p>Title: TOrderService</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月17日
 */
public interface TOrderService {

	TOrder createOrder(OrderInfoSubmitVo vo);

	void updateOrderStatus(String out_trade_no);

}
