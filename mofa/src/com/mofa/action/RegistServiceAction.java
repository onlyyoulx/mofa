package com.mofa.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mofa.dao.CodeDao;
import com.mofa.dao.UserDao;

import com.mofa.entity.Code;
import com.mofa.entity.User;

import com.opensymphony.xwork2.ActionSupport;

public class RegistServiceAction extends ActionSupport{
	private String phone;
	private String password;
	private String nickname;
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	private Map<String,Object> map;
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	//获取spring对象
   ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	UserDao userDao=(UserDao) ctx.getBean("userDao"); 
	CodeDao codeDao=(CodeDao) ctx.getBean("codeDao");
	
	
	
	public String execute(){
		
		User user=new User();
		List<User> users=(List<User>) userDao.findByPhone(phone);
		List<Code> codes=codeDao.findByPhone(phone);

		if(users.size()>0){
			map=new HashMap<String,Object>();
			map.put("message", "该手机已注册");
			map.put("status", 0);
			return "error";
		}
		if(codes.size()>0){
			
		for(Code co:codes){
			if(code.equals(co.getCode())){
				System.out.println("看看Code是什么：---"+co.getCode());
				codeDao.delete(co.getId());
				user.setPhone(phone);
				user.setPassword(password);
				user.setNickname(nickname);
				userDao.saveUser(user);
				map=new HashMap<String,Object>();
				map.put("datas", user);
				map.put("message", "注册成功");
				map.put("status", 1);
				
				return "success";
			}
		 }
		}else{
			System.out.println("看看有没有进来》。。。");
			map=new HashMap<String,Object>();
			map.put("message", "短信验证码有误");
			map.put("status", 0);
			return "error";
		}
		return "error";
	}
}
