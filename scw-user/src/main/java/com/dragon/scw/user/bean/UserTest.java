package com.dragon.scw.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserTest {

	@ApiModelProperty(value = "主键")
	private int id;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "电子邮件")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
