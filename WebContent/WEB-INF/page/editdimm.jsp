<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatedimmaction">
 	<input name="id" value=${dimm.id} hidden/>
    DIMM name: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${dimmscategory}" var="dimmcategory" varStatus="st">
    <option value = "${dimmcategory.id}" <c:if test="${dimmcategory.id == dimm.id}"> selected="selected" </c:if>>${dimmcategory.name }</option>
    </c:forEach>
    </select>
    <br>
    DIMM owner: <input type="text" name="owner" value=${dimm.owner}><br/>
    DIMM location: <input type="text" name="location" value=${dimm.location}><br />
	serial number: <input type="text" name="serialnumber" value=${dimm.serialnumber}><br/>
	label: <input type="text" name="label" value=${dimm.label}><br/> 	
    <input type="submit" value="Edit DIMM">
</form>