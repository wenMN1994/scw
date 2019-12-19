package com.dragon.scw.user.service;

import com.dragon.scw.user.vo.req.UserRegistVo;
import com.dragon.scw.user.vo.req.UserRespVo;

public interface TMemberService {

	int saveTMember(UserRegistVo vo);

	UserRespVo getUserByLogin(String loginacct, String password);

}
