<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="addmortherboard">
 
    MB name: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${mortherboards}" var="mortherboard" varStatus="st">
    <option value = "${mortherboard.id}">${mortherboard.description }</option>
    </c:forEach>
    </select>
    <br>
    MB owner: <input type="text" name="owner" value=""><br />
    Mac Address: <input type="text" name="macaddress" value=""><br />
	serial number: <input type="text" name="serialnumber" value=""><br/>
	label: <input type="text" name="label" value=""><br/> 	
    <input type="submit" value="Add MB">
</form>