<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatecpuaction">
 	<input name="id" value=${cpu.id} hidden/>
    CPU name: <select name="categoryId">
    <c:forEach items="${cpuscategory}" var="cpucategory" varStatus="st">
    <option value = "${cpucategory.id}" <c:if test="${cpucategory.id == cpu.id}"> selected="selected" </c:if>>${cpucategory.name }</option>
    </c:forEach>
    </select>
    <br>
    CPU owner: <input type="text" name="owner" value=${cpu.owner}><br />
    CPU location: <input type="text" name="location" value=${cpu.location} ><br />
	serial number: <input type="text" name="serialnumber" value=${cpu.serialnumber}><br/>
	label: <input type="text" name="label" value=${cpu.label} ><br/> 	
    <input type="submit" value="Edit CPU">
</form>