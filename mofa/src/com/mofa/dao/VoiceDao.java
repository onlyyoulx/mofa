package com.mofa.dao;

import java.util.List;

import com.mofa.entity.User;
import com.mofa.entity.Voices;

public interface VoiceDao {
	//查找所有的信息
	List<Voices> findByAll( int page,int pageSize);

	//增加用户
	Integer saveVoices(Voices voices);
    //分页查询
	
	
	
	

}
