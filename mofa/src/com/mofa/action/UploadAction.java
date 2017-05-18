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

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mofa.dao.UserDao;
import com.mofa.dao.VoiceDao;
import com.mofa.entity.Result;
import com.mofa.entity.User;
import com.mofa.entity.Voices;
import com.opensymphony.xwork2.ActionSupport;



public class UploadAction extends ActionSupport{
	private String phone;
    private String voice;
	private List<File> file;
	private List<String> fileFileName;
	private List<String> fileContentType;
	private List<String> dataUrl;
	private Map<String,Object> map;
	private Result rs;
	
	
	public Result getRs() {
		return rs;
	}
	public void setRs(Result rs) {
		this.rs = rs;
	}
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
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
	}
	public List<String> getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	public List<String> getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(List<String> dataUrl) {
		this.dataUrl = dataUrl;
	}
	
	//引入数据库操作
	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	VoiceDao voiceDao=(VoiceDao) ctx.getBean("voiceDao");
	UserDao userDao=(UserDao) ctx.getBean("userDao");
	
	
	List lists=new ArrayList<String>();
	
	public String execute(){
		List<User> users=userDao.findByPhone(phone);
		if(users.size()==0){
			map=new HashMap<String,Object>();
			map=new HashMap<String,Object>();
			map.put("message", "图片上传失败");
			map.put("status", 0);
			return "error";
			
		}
		
		
		//dadtaUrl保存图片上传的路径
		dataUrl=new ArrayList<String>();
		String imgpath="/C:/picture/";
		if(file.size()>0){
		for(int i=0;i<file.size();i++){
			try {
				InputStream is=new FileInputStream(file.get(i));   //file即为jsp页面点击上传后的对象
               // System.out.println("jsp传过来的对象file"+file.get(i));
                /**
                 * path:获取项目的路径目的是找到项目下面的文件夹然后保存图片，其实也可以不要可直接指定路径即可
                 */
				//String path=ServletActionContext.getServletContext().getRealPath("/"); //获取项目的路径
				
				//System.out.println("项目的路径"+path);    //C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\mofa\
				//获取上传文件file的文件名
				//System.out.println(this.getFileFileName());
				//对应一个个的file文件名
				//System.out.println(this.getFileFileName().get(i));  //文件名4.jpg，this.getFileFileName()所有的上传文件的名字
				//给上传的图片规定路径及命名
				String name=phone+System.currentTimeMillis()+i+this.getFileFileName().get(i).substring(this.getFileFileName().get(i).lastIndexOf("."), this.getFileFileName().get(i).length());
				lists.add(name);
				//System.out.println(System.currentTimeMillis());
				//System.out.println(name);
				dataUrl.add(imgpath+name);  //save+文件名.jpg
				
				//创建上传对象，找到对应的file上传对象及路径
				File destFile=new File(imgpath, name);
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
				
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
			
		}
		//进行数据库插入操作
		Voices voices=new Voices();
		voices.setPhone(phone);
		voices.setVoice(voice);
		System.out.println("我来看看字符串是什么："+lists);
		voices.setPictureUrl(lists);
		voiceDao.saveVoices(voices);
		map=new HashMap<String,Object>();
		map.put("message", "上传成功");
		map.put("status", 1);
		rs=new Result();
		rs.setLi(lists);
		rs.setV(voice);
		map.put("datas", rs);
		return SUCCESS;
		
		}else{
			map=new HashMap<String,Object>();
			map.put("message", "上传图片失败");
			map.put("status", 0);
			return "error";
		}
	
	}
	
}
