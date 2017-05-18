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
    
    <title>文件上传成功</title>

  </head>
  
  <body>
    说你想说: <s:property value="voice"/><br>
 
     file: <s:property value="fileFileName"/>
 
 
      <s:iterator id="imgUrl" value="dataUrl">
       <br /><img src="${imgUrl}"/>
       </s:iterator>
  </body>
</html>
