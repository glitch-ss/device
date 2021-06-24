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
<table style="width:90%; margin:44px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>nickname</td>
        <td>serialnumber</td>
        <td>owner</td>
        <td>location</td>
        <td>label</td>
        <td>description</td>
        <td>partnumber</td>
        <td>category</td>
        <td>width</td>
        <td>subvendor_id</td>
        <td>subdevice_id</td>
        <td>edit</td>
        <td>delete</td>
    </tr>
    <c:forEach items="${pcis}" var="pci" varStatus="st">
        <tr>
            <td>${pci.id}</td>
            <td>${pci.nickname}</td>
            <td>${pci.serialnumber}</td>
            <td>${pci.owner}</td>
            <td>${pci.location}</td>
            <td>${pci.label}</td>
            <td>${pci.description}</td>
			<td>${pci.partnumber}</td>
            <td>${pci.devicecategory}</td>
            <td>${pci.width}</td>
            <td>${pci.subvendorid}</td>
            <td>${pci.subdeviceid}</td>
            <td><a href="editpcicategory?id=${pci.id}">编辑</a></td>
            <td><a href="deletepcicategory?id=${pci.id}">删除</a></td>
        </tr>
    </c:forEach>
 
</table>
<nav>
<ul class="pager">
<li><a href="addpcicategory">Add</a></li>
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