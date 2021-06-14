<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatemortherboardaction">
 	<input name="id" value=mortherboard} hidden/>
    MB name: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${mortherboardscategory}" var="mortherboardcategory" varStatus="st">
    <option value = "${mortherboardcategory.id}" <c:if test="${mortherboardcategory.id == mortherboard.id}"> selected="selected" </c:if>>${mortherboardcategory.description }</option>
    </c:forEach>
    </select>
    <br>
    MB owner: <input type="text" name="owner" value=${mortherboard.owner}><br/>
	serial number: <input type="text" name="serialnumber" value=${mortherboard.serialnumber}><br/>
	Mac Address: <input type="text" name="macaddress" value=${mortherboard.macaddress}><br />
	label: <input type="text" name="label" value=${mortherboard.label}><br/> 	
    <input type="submit" value="Edit MB">
</form>