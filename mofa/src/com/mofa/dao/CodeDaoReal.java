package com.mofa.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mofa.entity.Code;
import com.mofa.entity.User;

public class CodeDaoReal implements CodeDao{
	
	//实例化一个HibernateTemplate对象，用于执行持久化操作
	private HibernateTemplate ht=null;
	//持久化所需要的SessionFactory
	private SessionFactory sessionFactory=null;
	


public HibernateTemplate getHt() {
	 if(ht==null){
		 ht=new HibernateTemplate(sessionFactory);
	 }
		return ht;
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//查找号码
	public List<Code> findByPhone(String phone) {
		
		return (List<Code>)getHt().find("from Code c where c.phone=?",phone);
	}
   //保存短信及号码
	public Integer save(Code code) {
		
		return (Integer) getHt().save(code);
	}
	
    //更新
	public void update(Code code) {
		getHt().update(code);
		
	}
    //按照id进行删除
	public void delete(int id) {
		Code code=new Code();
		code.setId(id);
		getHt().delete(code);
		
	}
    
}
