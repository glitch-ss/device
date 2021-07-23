<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatecpucategoryaction">
 	<input name="id" value=${cpu.id} hidden/>
    CPU name: <input type="text" name="name" value="${cpu.name}">
    cores: <input type="text" name="cores" value="${cpu.cores}"><br/>
    nickname: <input type="text" name="nickname" value="${cpu.nickname}"><br />
	brand: <input type="text" name="brand" value="${cpu.brand}"><br/>
	platform: <input type="text" name="platform" value="${cpu.platform}"><br/>
	frequency: <input type="text" name="frequency" value="${cpu.frequency}"><br/>
	sspec/partnumber: <input type="text" name="sspec" value="${cpu.sspec}"><br/>
	category: <input type="text" name="category" value="${cpu.category}"><br/>
	maxHz: <input type="text" name="maxHz" value="${cpu.maxHz}"><br/>
	minHz: <input type="text" name="minHz" value="${cpu.minHz}"><br/>
    <input type="submit" value="Edit CPU">
</form>