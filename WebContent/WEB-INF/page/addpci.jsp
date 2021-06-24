<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="addpci">
 
    nick name: <select name="categoryId">
    <option value=""></option>
    <c:forEach items="${pcis}" var="pci" varStatus="st">
    <option value = "${pci.id}">${pci.nickname }</option>
    </c:forEach>
    </select>
    <br>
    PCI owner: <input type="text" name="owner" value=""><br/>
    PCI location: <input type="text" name="location" value=""><br />
	serial number: <input type="text" name="serialnumber" value=""><br/>
	label: <input type="text" name="label" value=""><br/> 	
    <input type="submit" value="Add PCI">
</form>