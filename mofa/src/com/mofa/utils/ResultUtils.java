package com.mofa.utils;

/**
 * 返回结果的工具类
 * 
 * @author Jette
 * 
 */
public class ResultUtils {

	private static final String SUCCESS_CODE = "1";

	private static final String FAILURE_CODE = "0";

	/**
	 * 成功的结构体,200表示成功
	 * 
	 * @param obj
	 * @return String
	 */
	public static String toSuccess(Object datas) {
		Result result = new Result();
		result.setStatus(SUCCESS_CODE);
		result.setDatas(datas);
		return JsonUtils.parseObjectToJson(result);
	}

	public static String toSuccess(Object datas, String message) {
		Result result = new Result();
		result.setStatus(SUCCESS_CODE);
		result.setDatas(datas);
		result.setMessage(message);
		return JsonUtils.parseObjectToJson(result);
	}

//	public static String toSuccess(User up, String message) {
//		Result result = new Result();
//		result.setStatus(SUCCESS_CODE);
//		result.setDatas(up);
//		result.setMessage(message);
//		return JsonUtils.parseObjectToJson(result);
//	}

	/**
	 * 失败的结构体,400表示失败
	 * 
	 * @author Jette
	 * 
	 */
	public static String toFailure(String message) {
		Result result = new Result();
		result.setStatus(FAILURE_CODE);
		result.setMessage(message);
		return JsonUtils.parseObjectToJson(result);
	}

	public static String toFailure(Object datas, String message) {
		Result result = new Result();
		result.setStatus(FAILURE_CODE);
		result.setDatas(datas);
		result.setMessage(message);
		return JsonUtils.parseObjectToJson(result);
	}

	public static class Result {
		private String status;// 表示结果状态
		private Object datas;// 表示请求返回的数据体
		private String message;// 表示请求的描述

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Object getDatas() {
			return datas;
		}

		public void setDatas(Object datas) {
			this.datas = datas;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}
