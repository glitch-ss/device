<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<script>
$(function(){
    $("a").addClass("btn btn-default btn-xs");
     
});
 
</script>
<!DOCTYPE html>
<html>
<head>

</head>
<table style="width:80%; margin:44px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>owner</td>
        <td>serialnumber</td>
		<td>partnumber</td>
		<td>manufacture</td>
		<td>macaddress</td>
        <td>label</td>
        <td>edit</td>
        <td>delete</td>
    </tr>
    <c:forEach items="${mortherboards}" var="mortherboard" varStatus="st">
        <tr>
            <td>${mortherboard.id}</td>
            <td>${mortherboard.description}</td>
            <td>${mortherboard.owner}</td>
            <td>${mortherboard.serialnumber}</td>
            <td>${mortherboard.partnumber}</td>
            <td>${mortherboard.manufacture}</td>
            <td>${mortherboard.macaddress}</td>
            <td>${mortherboard.label}</td>
            <td><a href="editmortherboard?id=${mortherboard.id}">编辑</a></td>
            <td><a href="deletemortherboard?id=${mortherboard.id}">删除</a></td>
        </tr>
    </c:forEach>
 
</table>
<nav>
<ul class="pager">
<li><a href="addmortherboard">Add</a></li>
</ul>
</nav>
<nav>
  <ul class="pager">

    <li><a href="?start=0">首  页</a></li>
    <li><a href="?start=${pre}">上一页</a></li>
    <li><a href="?start=${next}">下一页</a></li>
    <li><a href="?start=${last}">末  页</a></li>
  </ul>           
</nav>
</html>