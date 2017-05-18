package com.mofa.utils;

import java.util.ArrayList;
import java.util.Map;

import com.alibaba.fastjson.JSON;


public class JsonUtils {

	/**
	 * 提取某段json
	 * 
	 * @param key
	 * @return
	 */
	public static String getJsonByKey(String json, String key) {
		if (TextUtils.isEmpty(json) || !json.contains(key)) {
			return "";
		} else {
			return JSON.parseObject(json).getString(key);
		}
	}

	/**
	 * 把对象转换成String
	 * 
	 * @param obj
	 * @return
	 */
	public static String parseObjectToJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * 把json装换成Bean对象
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T parseJsonToObject(String json, Class<T> cls) {
		return JSON.parseObject(json, cls);
	}

	/**
	 * 把集合转换成String
	 * 
	 * @param list
	 * @return
	 */
	public static String parseArrayToJson(ArrayList list) {
		return JSON.toJSONString(list);
	}

	/**
	 * 把json转换成list集合
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> ArrayList<T> parseJsonToArray(String json, Class<T> cls) {
		return (ArrayList<T>) JSON.parseArray(json, cls);
	}
	/**
	 * 把json转换为map
	 */
	public static Map<String,Object> parseJosnToMap(String json){
		return  (Map<String,Object>)JSON.parse(json);
	}
	
	
	
	
	
}
