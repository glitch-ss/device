<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatepciaction">
 	<input name="id" value=${pci.id} hidden/>
    PCI nickname: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${pciscategory}" var="pcicategory" varStatus="st">
    <option value = "${pcicategory.id}" <c:if test="${pcicategory.id == pci.id}"> selected="selected" </c:if>>${pcicategory.nickname }</option>
    </c:forEach>
    </select>
    <br>
    PCI owner: <input type="text" name="owner" value=${pci.owner}><br/>
    PCI location: <input type="text" name="location" value=${pci.location}><br />
	serial number: <input type="text" name="serialnumber" value=${pci.serialnumber}><br/>
	label: <input type="text" name="label" value=${pci.label}><br/> 	
    <input type="submit" value="Edit PCI">
</form>