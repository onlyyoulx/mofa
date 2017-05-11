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



public class regist extends HttpServlet{
     private String phone;
     private String password;
     private String user;
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
		   Connection conn=null;    //��ʼ������Ϊnull
		   PreparedStatement stat=null;  //��ʼ��ִ�б���Ϊnull
		   ResultSet rs=null;            //��ʼ����ý��Ϊnull
		   
		   String url="jdbc:mysql://localhsot:3306/wosai?" +
			           "user=root&password=wosai0769";
		   //����mysql���ݿ�����
		   
		   try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url);
			
			stat=conn.prepareStatement("select phone from user");
			rs=stat.executeQuery();
			while(rs.next()){
				user=rs.getString("phone");
			}if(phone.equals(user)){

				out.print("�û��Ѵ�����ֱ�ӵ�½");
			}else{
			    stat=conn.prepareStatement("insert into user(phone,password) values(?,?)");
			    stat.setString(1, phone);
			    stat.setString(2, password);		
			    stat.executeUpdate();
			    out.print("ע��ɹ�");
			    out.close();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		   
		   
		   
	}
     
	
}
