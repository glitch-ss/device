<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="adddimm">
 
    CPU name: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${dimms}" var="dimm" varStatus="st">
    <option value = "${dimm.id}">${dimm.name }</option>
    </c:forEach>
    </select>
    <br>
    DIMM owner: <input type="text" name="owner" value=""><br/>
    DIMM location: <input type="text" name="location" value=""><br />
	serial number: <input type="text" name="serialnumber" value=""><br/>
	label: <input type="text" name="label" value=""><br/> 	
    <input type="submit" value="Add DIMM">
</form>