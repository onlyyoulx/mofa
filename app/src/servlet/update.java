package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JsonUtils;

import beans.Result;
import beans.Update;

public class update extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 获取用户所有版本号
		int version;
		// ver获取数据库中的版本号变量
		int ver = 0;
		// versionname获取数据库版本的名字
		String versionname = null;
		// url获取数据库
		String ur = null;

		version = Integer.parseInt(req.getParameter("versionCode"));
		// 创建信息输出对象
		PrintWriter out = resp.getWriter();
		// 创建数据连接
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/wosai?"
				+ "user=root&password=wosai0769";
		try {
			// 加载数据驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			stat = conn.prepareStatement("select * from version");
			rs = stat.executeQuery();
			while (rs.next()) {
				ver = Integer.parseInt(rs.getString("versioncode"));
				versionname = rs.getString("versionname");
				ur = rs.getString("url");
			}
			Result result = new Result();
			if (version < ver) {
				// out.print(ur);
				// 要更新{status：0；datas：xxx；msg：发现新版本}
				// Result result = new Result();
				result.setStatus("0");
				result.setMsg("发现新版本");
				Update update = new Update();
				update.setUr(ur);
				update.setVersonCode(ver);
				update.setVersonName(versionname);
				result.setDatas(update);
				out.print(JsonUtils.parseObjectToJson(result));

			} else {
				// out.print("已是最新版本v1.0.3");
				// 没有更新，最新版本
				result.setStatus("1");
				result.setMsg("最新版本");
				out.print(JsonUtils.parseObjectToJson(result));
			}
			out.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}

}
