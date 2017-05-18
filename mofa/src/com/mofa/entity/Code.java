package com.mofa.entity;

import java.sql.Timestamp;

public class Code {
	private Integer id;
	private String code;
	private String phone;
	private Timestamp createTime;
	
	public Code(){
		
	}

	public Code(Integer id, String code, String phone, Timestamp createTime) {
		super();
		this.id = id;
		this.code = code;
		this.phone = phone;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	

}
