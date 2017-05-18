package com.mofa.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mofa.dao.UserDao;
import com.mofa.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginServiceAction extends ActionSupport{
	private String phone;
	private String password;
	private User user;
	private String key="just see see";
	
	
	//设置key属性不作为json的内容返回  
    @JSON(serialize=false) 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	UserDao userDao=(UserDao) ctx.getBean("userDao");

	public String execute(){
		//查找账号相符的用户
		List<User> users=userDao.findByPhone(phone);
		if(users.size()>0){
		for(User user:users){
		//使用简化的for语句对集合进行遍历并比较用户的密码
			if(user.getPassword().equals(password)){
				User u=new User();
				u.setId(user.getId());
				u.setPhone(user.getPhone());
				u.setPassword(user.getPassword());
				u.setNickname(user.getNickname());
				u.setOnlineValue(user.getOnlineValue());
				u.setPhotoUrl(user.getPhotoUrl());
				map=new HashMap<String,Object>();
				map.put("datas", u);
				map.put("message", "登录成功");
				map.put("status", 1);
				return "success" ;
			}else{
				map=new HashMap<String,Object>();
				map.put("message", "用户名或密码错误");
				map.put("status", 0);
				return "error";
			}
			
	    }	
	}else { 
		map=new HashMap<String,Object>();
		map.put("message", "用户名或密码错误");
		map.put("status", 0);
		return "error"; 
	   }
		return "error";
	}
	
	private Map<String, Object> map;

	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}





}
