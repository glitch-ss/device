 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form action="updatedimmcategoryaction">
 	<input type="text" name="id" value=${dimm.id } hidden=hidden>
 	name: <input type="text" name="name" value=${dimm.name}><br/>
    nick name: <input type="text" name="nickname" value=${dimm.nickname}><br/>
    size: <input type="text" name="size" value=${dimm.size}><br/>
    brand: <input type="text" name="brand" value="${dimm.brand}"><br />
	speed: <input type="text" name="speed" value="${dimm.speed}"><br/>
	platform: <input type="text" name="platform" value=${dimm.platform}><br/>
	partnumber: <input type="text" name="partnumber" value="${dimm.partnumber}"><br />
	type: <input type="text" name="type" value="${dimm.type}"><br/>
	rank: <input type="text" name="rank" value=${dimm.rank}><br/>
    <input type="submit" value="Edit DIMMCategory">
</form>