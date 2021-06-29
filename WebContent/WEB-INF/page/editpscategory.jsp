<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatepscategoryaction">
 
	<input type="text" name="id" value="${ps.id}" hidden=hidden>
    description: <input type="text" name="description" value="${ps.description}"><br/>
    partnumber: <input type="text" name="partnumber" value="${ps.partnumber}"><br />
	manufacture: <input type="text" name="manufacture" value="${ps.manufacture}"><br/>
    <input type="submit" value="Edit PSCategory">
</form>