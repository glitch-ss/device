 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form action="adddimmcategoryaction">
 	name: <input type="text" name="name"><br/>
    nick name: <input type="text" name="nickname"><br/>
    size: <input type="text" name="size"><br/>
    brand: <input type="text" name="brand"><br />
	speed: <input type="text" name="speed"><br/>
	platform: <input type="text" name="platform"><br/>
	partnumber: <input type="text" name="partnumber"><br />
	type: <input type="text" name="type"><br/>
	rank: <input type="text" name="rank"><br/>
    <input type="submit" value="Add DIMMCategory">
</form>