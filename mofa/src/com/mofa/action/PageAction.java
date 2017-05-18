package com.mofa.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mofa.dao.VoiceDao;
import com.mofa.entity.Voices;
import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport{
	private Integer page;
	private Integer pageSize;
	private Map<String,Object> map;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	
public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	 ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	 
	 VoiceDao voiceDao=(VoiceDao) ctx.getBean("voiceDao");
  
	public String execute(){
		List<Voices> lists= (List<Voices>) voiceDao.findByAll(page, pageSize);
	  if(page!=null&&pageSize!=null){
		  map=new HashMap<String,Object>();
		  map.put("datas", lists);
		  map.put("message", "加载成功");
		  map.put("status", 1);
		  return SUCCESS;
	  }else{
		  map=new HashMap<String,Object>();
		  map.put("message", "加载失败");
		  map.put("status", 0);
		  return ERROR;
	  }
			
	}
	
	
	
	
	
	

}
