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
		//���ñ���ͽ����ʽΪutf-8
		   req.setCharacterEncoding("utf-8");
		   resp.setContentType("text/html;charset=utf-8");
		   //��ȡ�ͻ��˵��û�����phone ���룺password 
		   phone=req.getParameter("phone");
		   password=req.getParameter("password");
		   
		   //�������������
		   PrintWriter out=resp.getWriter();
		   //�������ݿ�����
		   Connection conn=null;
		   PreparedStatement stat=null;
		   ResultSet rs=null;
		   String url="jdbc:mysql://localhsot:3306/wosai?" +
			           "user=root&password=wosai0769";
		   //����mysql���ݿ�����
		     
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
				out.println("��½�ɹ�");
				out.println("�û�����"+phone);
				out.println("���룺"+password);
				out.close();
			}else{
				out.print("�û������������������...");
			}
		  
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("ϵͳ��æ������...");
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
