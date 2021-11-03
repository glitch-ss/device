<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="logincheck">
 
    name: <input type="text" name="name" value=""><br />
	email: <input type="text" name="email" value=""><br/>
	password: <input type="text" name="password" value=""><br/> 	
    <input type="submit" value="LoginIn">
    <a href="signin">signin</a>
</form>
<h1>${message }</h1>