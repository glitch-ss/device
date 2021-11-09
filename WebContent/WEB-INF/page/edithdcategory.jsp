<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatehdcategoryaction">
 
    <input type="text" name="id" value="${hd.id }" hidden=hidden>
    partnumber: <input type="text" name="partnumber" value="${hd.partnumber }"><br />
    size: <input type="text" name="size" value="${hd.size }"><br/>
    capacity: <input type="text" name="capacity" value="${hd.capacity }"><br/>
	manufacture: <input type="text" name="manufacture" value="${hd.manufacture }"><br/>
	type: <input type="text" name="type" value="${hd.type }"><br/>
    <input type="submit" value="Edit HD">
</form>