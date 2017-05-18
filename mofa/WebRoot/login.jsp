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
    
    <title>用户登录</title>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
  </head>
  <body>
    <h1>欢迎登录</h1>
   <div>
    <s:form action="toLogin" method="post">
    <s:textfield name="phone" label="账号"></s:textfield>
    <s:password name="password" label="密码"></s:password>
    <s:submit value="登录"/>
    </s:form>
   </div>
  
  </body>
</html>
