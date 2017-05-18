package com.mofa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mofa.dao.UserDao;
import com.mofa.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction2 extends ActionSupport{
	
	private String phone;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private Map <String,Object> map;
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	//引入数据库操作
	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	UserDao userDao=(UserDao) ctx.getBean("userDao");
	
	public String execute(){
		
		 String div="/C:/photo/";
		 
		 try {
			//获取jsp传过来的file对象
			InputStream is=new FileInputStream(file);
			//创建保存的文件名
		String name=phone+System.currentTimeMillis()+this.getFileFileName().substring(this.getFileFileName().lastIndexOf("."), this.getFileFileName().length());
			//获取到文件名后保存数据库
			List<User> users=userDao.findByPhone(phone);
			
			if(users.size()>0){
			for(User u:users){
				u.setPhotoUrl(name);
				userDao.updatePhoto(u);
	
			}
			//创建上传对象，找到对应的file上传对象及路径
			File destFile=new File(div, name);
			//创建输出流
			OutputStream os=new FileOutputStream(destFile);
			byte[] buffer=new byte[500];
			int length=0;
			while((length=is.read(buffer))>0){
				//写入对象
				os.write(buffer, 0, length);
			   }
			is.close();
			os.close();	
			   map=new HashMap<String,Object>();
			   map.put("message", "上传成功");
			   map.put("status", 1);
			   map.put("datas", name);
			   return SUCCESS;
			   

			}else{
				map=new HashMap<String,Object>();
				map.put("message", "上传错误，请重试");
				map.put("status", 0);
				return "error";
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
