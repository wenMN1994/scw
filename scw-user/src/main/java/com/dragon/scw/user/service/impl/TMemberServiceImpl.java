package com.dragon.scw.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragon.scw.enums.UserExceptionEnum;
import com.dragon.scw.user.bean.TMember;
import com.dragon.scw.user.bean.TMemberAddress;
import com.dragon.scw.user.bean.TMemberAddressExample;
import com.dragon.scw.user.bean.TMemberExample;
import com.dragon.scw.user.exception.UserException;
import com.dragon.scw.user.mapper.TMemberAddressMapper;
import com.dragon.scw.user.mapper.TMemberMapper;
import com.dragon.scw.user.service.TMemberService;
import com.dragon.scw.user.vo.req.UserRegistVo;
import com.dragon.scw.user.vo.req.UserRespVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly=true)
public class TMemberServiceImpl implements TMemberService {

	@Autowired
	TMemberMapper memberMapper;
	
	TMemberAddressMapper memberAddressMapper;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Transactional
	@Override
	public int saveTMember(UserRegistVo vo) {
		try {
			//将vo转业务能用的数据对象
			TMember member = new TMember();
			BeanUtils.copyProperties(vo, member);
			// 2、不存在，保存信息；设置默认信息
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encode = encoder.encode(member.getUserpswd());
			//设置密码
			member.setUserpswd(encode);
			int i = memberMapper.insert(member);
			log.debug("注册会员成功-{}",member);
			return i;
		} catch (Exception e) {
			log.debug("注册会员失败-{}",e.getMessage());
			e.printStackTrace();
			throw new UserException(UserExceptionEnum.USER_SAVE_ERROR);
		}
	}

	@Override
	public UserRespVo getUserByLogin(String loginacct, String password) {
		UserRespVo vo = new UserRespVo();
		
		TMemberExample example = new TMemberExample();
		example.createCriteria().andLoginacctEqualTo(loginacct);
		List<TMember> list = memberMapper.selectByExample(example);
		
		if(list == null || list.size() == 0) {
			throw new UserException(UserExceptionEnum.LOGINACCT_UNEXIST);
		}
		
		TMember member = list.get(0);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(!encoder.matches(password, member.getUserpswd())) {
			throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
		}
		
		BeanUtils.copyProperties(member, vo);
		String accessToken = UUID.randomUUID().toString().replace("-", "");
		vo.setAccessToken(accessToken);
		
		stringRedisTemplate.opsForValue().set(accessToken, member.getId().toString());
		return vo;
	}

	@Override
	public TMember geUserById(Integer memberid) {
		
		return memberMapper.selectByPrimaryKey(memberid);
	}

	@Override
	public List<TMemberAddress> getUserAddress(Integer memberId) {
		TMemberAddressExample example = new TMemberAddressExample();
		example.createCriteria().andMemberidEqualTo(memberId);
		memberAddressMapper.selectByExample(example);
		return memberAddressMapper.selectByExample(example);
	}
}
