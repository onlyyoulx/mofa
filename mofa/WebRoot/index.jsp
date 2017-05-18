<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片上传</title>

  </head>
  
  <body>
   <s:form action="toUpload" enctype="multipart/form-data">
    <s:textfield name="voice" label="想说的"/>
    <s:textfield name="phone" label="用户电话"/>
    <s:file name="file" label="请选择上传图片"/>
   <s:file name="file" label="请选择上传图片"/>
   <s:file name="file" label="请选择上传图片"/>
    <s:submit value="上传"></s:submit>
    <s:reset value="重置"/>

   </s:form>

  </body>
</html>
