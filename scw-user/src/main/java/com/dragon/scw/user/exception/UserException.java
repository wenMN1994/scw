package com.dragon.scw.user.exception;

import com.dragon.scw.enums.UserExceptionEnum;

public class UserException extends RuntimeException {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		
	}
	
	public UserException(UserExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
	}

}
