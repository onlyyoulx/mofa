package com.mofa.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * 请求头信息
 * @author Jette
 *
 */
public class ResponseUtils {
	
	/**
	 * 设置返回的头信息
	 * @param resp
	 * @return
	 */
	public static HttpServletResponse config(HttpServletResponse resp){
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		return resp;
	}

}
