<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="addhdcategory">
 
    
    partnumber: <input type="text" name="partnumber" value=""><br />
    size: <input type="text" name="size" value=""><br/>
    capacity: <input type="text" name="capacity" value=""><br/>
	manufacture: <input type="text" name="manufacture" value=""><br/>
	type: <input type="text" name="type" value=""><br/>
    <input type="submit" value="Add HD">
</form>