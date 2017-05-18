<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">

  </head>
  
  <body>
    <h1>欢迎注册</h1>
   <div>
    <s:form action="regist" method="post">
    <s:textfield name="phone" label="账号"></s:textfield>
    <s:password name="password" label="密码"></s:password>
    <s:textfield name="nickname" label="昵称"></s:textfield>
    <s:textfield name="code" label="验证短信"></s:textfield>
    <s:submit value="提交"/>
    </s:form>
   </div>
  </body>
</html>
