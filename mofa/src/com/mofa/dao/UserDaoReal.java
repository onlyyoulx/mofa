package com.mofa.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;



import com.mofa.entity.User;
import com.mofa.utils.HibernateUtil;

public class UserDaoReal implements UserDao{
	
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

	//查找用户
	
	public List<User> findByPhone(String phone) {
		
		return (List<User>)getHt().find("from User u where u.phone=?",phone);
	}

//增加用户信息
	public Integer saveUser(User user) {
		return  (Integer) getHt().save(user);
	}
//修改用户信息
	public void update(User user) {
		String hql="update User u set u.photoUrl=? where phone=u.phone";
		//获取session
		Session session=sessionFactory.openSession();
		Query q=session.createQuery(hql);
		q.setParameter(0, user.getPhotoUrl());

		//开启事务
		Transaction ts=session.beginTransaction();
		try{
			session.update(user);
			ts.commit();
		}catch(HibernateException e1){
			e1.printStackTrace();
			ts.rollback();
		}finally{
			session.close();
		}
		
	}
//修改用户信息，建议使用这种方法
	public void updatePhoto(User user) {
		getHt().update(user);
		
	}


}
