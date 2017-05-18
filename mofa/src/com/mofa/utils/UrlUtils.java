package com.mofa.utils;

public class UrlUtils {

	// 方法一：通过java.util.ResourceBundle读取资源属性文件
	private static String getPropertyByName(String path, String name) {
		String result = "";

		try {
			// 方法一：通过java.util.ResourceBundle读取资源属性文件
			result = java.util.ResourceBundle.getBundle(path).getString(name);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 拿去获取验证码的请求
	 * 
	 * @param phone
	 * @param content
	 * @return
	 */
	public static String getIdentifyUrl(String phone, String content) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("http://222.73.117.138:7891/mt?");
		buffer.append("un=N8890281&");
		buffer.append("pw=b1c6e84d&");
		buffer.append("da=" + phone + "&");
		buffer.append("sm=" + content + "&");
		buffer.append("dc=15&rd=1&tf=3&rf=2");

		return buffer.toString();
	}

}
