<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="/Web/servlet/Login" method="get">
   参数1:<input name="param1" type="text"/><br/>
   what:<input name="what" type="text"/><br/>
   <input type="submit" value="测试"/>
    </form>
  
  <body>
    <form action="/Web/servlet/Login" method="get">
   参数1:<input name="param1" type="text"/><br/>
   参数2:<input name="param2" type="text"/><br/>
   what:<input name="what" type="text"/><br/>
   <input type="submit" value="测试"/>
    </form>
    
    <form action="/Web/servlet/Login" method="get">
   参数1:<input name="param1" type="text"/><br/>
   参数2:<input name="param2" type="text"/><br/>
   参数3:<input name="param3" type="text"/><br/>
  what:<input name="what" type="text"/><br/>
   <input type="submit" value="测试"/>
    </form>
    
    <form action="/Web/servlet/Login" method="get">
   参数1:<input name="param1" type="text"/><br/>
   参数2:<input name="param2" type="text"/><br/>
   参数3:<input name="param3" type="text"/><br/>
   参数4:<input name="param4" type="text"/><br/>
   what:<input name="what" type="text"/><br/>
   <input type="submit" value="测试"/>
    </form>
       
    <form action="/Web/servlet/UploadServlet2" method="post" enctype="multipart/form-data">
	上传文件：<input name="file" type="file"/>
	<p /><input type="submit" value="上传" style="width: 50px" />
	</form>
    
    <p align="center"> 请您选择需要上传的文件</p>
	<form id="form1" name="form1" method="post" action="/Web/servlet/FileUploadServlet" enctype="multipart/form-data">
	 <table border="0" align="center">
	  <tr>
	   <td>上传人：</td>
	   <td>
	    <input name="name" type="text" id="name" size="20" ></td>
	  </tr>   
	  <tr>
	   <td>上传文件：</td>
	   <td><input name="file" type="file" size="20" ></td>
	  </tr>    
	  <tr>   
	   <td></td><td>
	    <input type="submit" name="submit" value="提交" >
	    <input type="reset" name="reset" value="重置" >
	   </td>
	  </tr>
	 </table>
	</form>
    
	
  </body>
</html>
