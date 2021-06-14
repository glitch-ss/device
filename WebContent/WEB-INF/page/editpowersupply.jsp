<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatepowersupplyaction">
 	<input name="id" value=${powersupply.id} hidden/>
    PS name: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${powersupplyscategory}" var="powersupplycategory" varStatus="st">
    <option value = "${powersupplycategory.id}" <c:if test="${powersupplycategory.id == powersupply.id}"> selected="selected" </c:if>>${powersupplycategory.description }</option>
    </c:forEach>
    </select>
    <br>
    PS owner: <input type="text" name="owner" value=${powersupply.owner}><br/>
    PS location: <input type="text" name="location" value=${powersupply.location}><br />
	serial number: <input type="text" name="serialnumber" value=${powersupply.serialnumber}><br/>
	label: <input type="text" name="label" value=${powersupply.label}><br/> 	
    <input type="submit" value="Edit PS">
</form>