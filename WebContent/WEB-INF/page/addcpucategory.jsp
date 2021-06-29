<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="addcpucategory">
 
    CPU name: <input type="text" name="name">
    cores: <input type="text" name="cores" value=""><br/>
    nickname: <input type="text" name="nickname" value=""><br />
	brand: <input type="text" name="brand" value=""><br/>
	platform: <input type="text" name="platform" value=""><br/>
	frequency: <input type="text" name="frequency" value=""><br/> 
	category: <input type="text" name="category" value=""><br/> 
    <input type="submit" value="Add CPU">
</form>