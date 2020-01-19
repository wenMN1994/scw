package com.dragon.scw.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.dragon.scw.enums.OrderStatusEnumes;
import com.dragon.scw.order.bean.TOrder;
import com.dragon.scw.order.bean.TOrderExample;
import com.dragon.scw.order.mapper.TOrderMapper;
import com.dragon.scw.order.service.TOrderService;
import com.dragon.scw.order.service.TProjectServiceFeign;
import com.dragon.scw.order.vo.req.OrderInfoSubmitVo;
import com.dragon.scw.order.vo.resp.TReturn;
import com.dragon.scw.utils.AppDateUtils;
import com.dragon.scw.vo.resp.AppResponse;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * <p>Title: TOrderServiceImpl</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2020年1月17日
 */
@Slf4j
@Service
public class TOrderServiceImpl implements TOrderService {
	
	@Autowired
	TOrderMapper orderMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@Override
	public TOrder createOrder(OrderInfoSubmitVo vo) {
		TOrder order = new TOrder();
		String accessToken = vo.getAccessToken();
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		//Integer userId = Integer.getInteger(memberId);
		
		log.debug("保存订单会员ID={}",memberId);
		
		order.setMemberid(Integer.parseInt(memberId));
		order.setProjectid(vo.getProjectid());
		order.setReturnid(vo.getReturnid());
		
		String ordernum = UUID.randomUUID().toString().replaceAll("-", "");
		order.setOrdernum(ordernum);
		
		order.setCreatedate(AppDateUtils.getFormatTime());
		
		AppResponse<TReturn> respTReturn = projectServiceFeign.returnInfo(vo.getReturnid());//调用远程服务
		TReturn retObj = respTReturn.getData();
		log.debug("项目回报信息={}", retObj);
		Integer totalMomey = vo.getRtncount() * retObj.getSupportmoney() + retObj.getFreight();
		order.setMoney(totalMomey);
		
		order.setRtncount(vo.getRtncount());
		order.setStatus(OrderStatusEnumes.UNPAY.getCode()+"");
		order.setAddress(vo.getAddress());
		order.setInvoice(vo.getInvoice().toString());
		order.setInvoictitle(vo.getInvoictitle());
		order.setRemark(vo.getRemark());
		
		orderMapper.insertSelective(order);
		
		log.debug("业务层保存订单={}", order);
		
		return order;
	}

	@Override
	public void updateOrderStatus(String out_trade_no) {
		List<TOrder> order = new ArrayList<TOrder>();
		TOrderExample example = new TOrderExample();
		example.createCriteria().andOrdernumEqualTo(out_trade_no);
		order = orderMapper.selectByExample(example);
		for (TOrder updateOrder : order) {
			BeanUtils.copyProperties(order, updateOrder);
			updateOrder.setStatus("1");
			orderMapper.updateByExample(updateOrder, example);
		}
		
	}

}
