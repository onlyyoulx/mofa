package com.mofa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;


import com.mofa.entity.Voices;

public class VoiceDaoReal implements VoiceDao{
	
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

    //倒序查找所有的信息
	
	public List<Voices> findByAll(int page,int pageSize) {
		
		String hql="from Voices v order by v.id desc";
		Session session=this.sessionFactory.openSession();
		Query query=session.createQuery(hql);
		 //追加分页参数设置
		  int from=(page-1)*pageSize;
		  query.setFirstResult(from);   //分页的起点，这里从0开始
		  query.setMaxResults(pageSize);  //每页有多少条数据
		  List<Voices>  voices= query.list();
		return voices;
	}
    //保存所有的信息
	public Integer saveVoices(Voices voices) {
		
		return (Integer) getHt().save(voices);
	}

}
