 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  

<form action="updatepcicategoryaction">
 
    nick name: <input type="text" name="nickname" value=${pci.nickname}><br/>
    device category: <input type="text" name="devicecategory" value=${pci.devicecategory}><br/>
    description: <input type="text" name="description" value=${pci.description}><br />
	part number: <input type="text" name="partnumber" value=${pci.partnumber}><br/>
	part number_ext: <input type="text" name="partnumberext" value=${pci.partnumberext}><br/>
	subsystem: <input type="text" name="subsystem" value=${pci.subsystem}><br />
	productname: <input type="text" name="productname" value=${pci.productname}><br/>
	width: <input type="text" name="width" value=${pci.width}><br/>
	vendorid: <input type="text" name="vendorid" value=${pci.vendorid}><br/>
	deviceid: <input type="text" name="deviceid" value=${pci.deviceid}><br/>
	subvendorid: <input type="text" name="subvendorid" value=${pci.subvendorid}><br/>
	subdeviceid: <input type="text" name="subdeviceid" value=${pci.subdeviceid}><br/>
	kernelmodule: <input type="text" name="kernelmodule" value=${pci.kernelmodule}><br/>
    <input type="submit" value="Edit PCIeCategory">
</form>