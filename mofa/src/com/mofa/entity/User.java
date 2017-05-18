package com.mofa.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

public class User implements Serializable{
	
	private Integer id;
	private String phone;
	private String nickname;
	private String password;
	private String onlineValue;
	private String photoUrl;
	private Timestamp createTime;
	
	
	
	public User(Integer id, String phone, String nickname, String password,
			String onlineValue, String photoUrl, Timestamp createTime) {
		super();
		this.id = id;
		this.phone = phone;
		this.nickname = nickname;
		this.password = password;
		this.onlineValue = onlineValue;
		this.photoUrl = photoUrl;
		this.createTime = createTime;
	}

	public User(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOnlineValue() {
		return onlineValue;
	}
	public void setOnlineValue(String onlineValue) {
		this.onlineValue = onlineValue;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	

}
