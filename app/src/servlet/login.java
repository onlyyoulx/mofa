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

public class login extends HttpServlet{
    private String phone;
    private String password;
    private String user;
    private String pwd;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		   doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置编译和解码格式为utf-8
		   req.setCharacterEncoding("utf-8");
		   resp.setContentType("text/html;charset=utf-8");
		   //获取客户端的用户名：phone 密码：password 
		   phone=req.getParameter("phone");
		   password=req.getParameter("password");
		   
		   //创建输出流对象
		   PrintWriter out=resp.getWriter();
		   //建立数据库连接
		   Connection conn=null;
		   PreparedStatement stat=null;
		   ResultSet rs=null;
		   String url="jdbc:mysql://localhsot:3306/wosai?" +
			           "user=root&password=wosai0769";
		   //加载mysql数据库驱动
		     
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url);
			stat=conn.prepareStatement("select phone,password from user");
			rs=stat.executeQuery();
			while(rs.next()){
				user=rs.getString("phone");
				pwd=rs.getString("password");
			     }
			if (phone.equals(user)&&password.equals(pwd)){
				out.println("登陆成功");
				out.println("用户名："+phone);
				out.println("密码："+password);
				out.close();
			}else{
				out.print("用户名或密码错误，请重试...");
			}
		  
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("系统繁忙请重试...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
	

}
