package beans;

import java.io.Serializable;

/**
 * 所有服务端请求的结果
 * 
 * @author Jette
 *
 */
public class Result implements Serializable {

	/**
	 * 0代表成,1代表失败
	 */
	private String status;

	/**
	 * 处理实际对象的信息
	 */
	private Object datas;

	/**
	 * 放回信息
	 */
	private String msg;

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", datas=" + datas + ", msg=" + msg + "]";
	}

}
