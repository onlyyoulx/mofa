package com.mofa.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

public class Voices implements Serializable{
	private Integer id;
	private String phone;
	private List pictureUrl;
	private String voice;
	private Timestamp createTime;
	
	
	public Voices(){
		
	}
	
	public Voices(Integer id, String phone, List pictureUrl, String voice,
			Timestamp createTime) {
		super();
		this.id = id;
		this.phone = phone;
		this.pictureUrl = pictureUrl;
		this.voice = voice;
		this.createTime = createTime;
	}







	public List getPictureUrl() {
		return pictureUrl;
	}







	public void setPictureUrl(List pictureUrl) {
		this.pictureUrl = pictureUrl;
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
	
	
	

	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	

}
