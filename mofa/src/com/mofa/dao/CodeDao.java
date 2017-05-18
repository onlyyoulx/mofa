package com.mofa.dao;

import java.util.List;

import com.mofa.entity.Code;

public interface CodeDao {
	//查找code数据库所对应的短信和phone
	List<Code> findByPhone(String phone);
	//写入
	Integer save(Code code);
	//更新短信
	void update(Code code);
	//删除短信
	void delete(int id);

	

}
