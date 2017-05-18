package com.mofa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.alibaba.fastjson.JSON;
import com.mofa.dao.CodeDao;
import com.mofa.dao.UserDao;
import com.mofa.entity.Code;
import com.mofa.entity.User;
import com.mofa.utils.ChuanglanSMS;
import com.mofa.utils.CodeUtils;

import com.opensymphony.xwork2.ActionSupport;

public class GetCodeServiceAction extends ActionSupport {
	private String code;
	private String phone;
	private Map<String,Object> map;
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
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	//加载spring配置文件获取spring 容器
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	CodeDao codeDao=(CodeDao) ctx.getBean("codeDao");
	UserDao userDao=(UserDao) ctx.getBean("userDao");
	//获取短信发送工具类
	ChuanglanSMS client = new ChuanglanSMS("N8890281","b1c6e84d");
	CloseableHttpResponse response = null;
	//编写短信验证码和短信内容
	int duanxin=CodeUtils.getCode();
	String duanxinStr=duanxin+"";
	String content="您的短信验证码为:"+duanxin;

	public String execute(){
		//查找手机号是否已经注册，如果已注册提示该用户已注册
		List<User> users=userDao.findByPhone(phone);
		//如果该phone已注册
		if(users.size()>0){			
			map=new HashMap<String,Object>();
			map.put("message", "该用户已注册");
			map.put("status", 0);
			return "error";
		}
		
		List<Code> codes=codeDao.findByPhone(phone);
		//如果短信存在先删除短信，再发送短信
			for(Code c:codes){
		    codeDao.delete(c.getId());
			}
		
		   //开始发送短信并写入数据库
			//发送短信
			response = client.sendMessage(phone,content);

			if(response != null && response.getStatusLine().getStatusCode()==200){
				
				try {
					System.out.println("有没有看到我啊。。。");
					Map dxmaps=JSON.parseObject(EntityUtils.toString(response.getEntity()));
					System.out.println("准备要进入Map了。。。");
					for(Object obj:dxmaps.keySet()){
						System.out.println("key为:"+obj+"值为:"+dxmaps.get(obj));
					
						if(obj.equals("r")){
							System.out.println(dxmaps.get(obj));
							map=new HashMap<String,Object>();
							map.put("message", "短信发送不成功");
							map.put("status", 0);
							return "error";
						  
					    }else{
						//写入数据库
						Code co=new Code();
						co.setPhone(phone);
						co.setCode(duanxinStr);
						codeDao.save(co);
						 map=new HashMap<String,Object>();
				            map.put("message", "获取短信成功");
							map.put("status", 1);
							return "success";
					    }
					}
				} catch (ParseException e) {				
					e.printStackTrace();
				} catch (IOException e) {					
					e.printStackTrace();
				}
				
			}
			return "success";
	}					
}
