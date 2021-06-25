<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="addpcicategory">
 
    nick name: <input type="text" name="nickname" value=""><br/>
    device category: <input type="text" name="devicecategory" value=""><br/>
    description: <input type="text" name="description" value=""><br />
	part number: <input type="text" name="partnumber" value=""><br/>
	part number_ext: <input type="text" name="partnumberext" value=""><br/>
	subsystem: <input type="text" name="subsystem" value=""><br />
	productname: <input type="text" name="productname" value=""><br/>
	width: <input type="text" name="width" value=0 placeholder=""><br/>
	vendorid: <input type="text" name="vendorid" value=""><br/>
	deviceid: <input type="text" name="deviceid" value=""><br/>
	subvendorid: <input type="text" name="subvendorid" value=""><br/>
	subdeviceid: <input type="text" name="subdeviceid" value=""><br/>
	kernelmodule: <input type="text" name="kernelmodule" value=""><br/>
    <input type="submit" value="Add PCIeCategory">
</form>