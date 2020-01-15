package com.dragon.scw.user.service;

import java.util.List;

import com.dragon.scw.user.bean.TMember;
import com.dragon.scw.user.bean.TMemberAddress;
import com.dragon.scw.user.vo.req.UserRegistVo;
import com.dragon.scw.user.vo.req.UserRespVo;

public interface TMemberService {

	int saveTMember(UserRegistVo vo);

	UserRespVo getUserByLogin(String loginacct, String password);

	TMember geUserById(Integer memberid);

	List<TMemberAddress> getUserAddress(Integer memberId);

}
