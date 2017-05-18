package com.mofa.dao;

import java.sql.Timestamp;
import java.util.List;

import com.mofa.entity.User;

public interface UserDao {
	//更新用户
	 void update(User user);
	//增加用户
	 Integer saveUser(User user);
	//查找用户
	List<User> findByPhone(String phone);
	//修改用户图片地址信息
	void updatePhoto(User user );
	
	
	

}
