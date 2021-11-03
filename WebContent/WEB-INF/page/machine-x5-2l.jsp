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
<body>
<div id="app">
<h3>${name}</h3> <button id="update" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">update</button>
<h2>CPU</h2>
<table style="width:100%; margin:0 auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    <tr><td>Name</td><td>Part Number</td><td>Description</td><td>Action</td></tr>
    <tr><td>CPU0</td><td>${machine.cpu.CPU_0.partnumber}</td><td>${machine.cpu.CPU_0.model}</td><td><a href="editmcpu?id=CPU0">edit</a></td></tr>
    <tr><td>CPU1</td><td>${machine.cpu.CPU_1.partnumber}</td><td>${machine.cpu.CPU_1.model}</td><td><a href="editmcpu?id=CPU1">edit</a></td></tr>
</table>
<h2>DIMM</h2>
<div class="row">
<table class="col-md-5 dimm table-bordered table-hover" style="margin:30px" align='center' border='1' cellspacing='0'>
    	<tr><td>Location</td><td>Part Number</td><td>Serial Number</td><td>Action</td></tr>
    	<tr><td>P0/D0</td><td>${machine.dimm['P0/D0'].partnumber}</td><td>${machine.dimm['P0/D0'].serialnumber}</td><td><a href="editmdimm?id=P0/D0">edit</a></td></tr>
    	<tr><td>P0/D1</td><td>${machine.dimm['P0/D1'].partnumber}</td><td>${machine.dimm['P0/D1'].serialnumber}</td><td><a href="editmdimm?id=P0/D1">edit</a></td></tr>
    	<tr><td>P0/D2</td><td>${machine.dimm['P0/D2'].partnumber}</td><td>${machine.dimm['P0/D2'].serialnumber}</td><td><a href="editmdimm?id=P0/D2">edit</a></td></tr>
    	<tr><td>P0/D3</td><td>${machine.dimm['P0/D3'].partnumber}</td><td>${machine.dimm['P0/D3'].serialnumber}</td><td><a href="editmdimm?id=P0/D3">edit</a></td></tr>
    	<tr><td>P0/D4</td><td>${machine.dimm['P0/D4'].partnumber}</td><td>${machine.dimm['P0/D4'].serialnumber}</td><td><a href="editmdimm?id=P0/D4">edit</a></td></tr>
    	<tr><td>P0/D5</td><td>${machine.dimm['P0/D5'].partnumber}</td><td>${machine.dimm['P0/D5'].serialnumber}</td><td><a href="editmdimm?id=P0/D5">edit</a></td></tr>
    	<tr><td>P0/D6</td><td>${machine.dimm['P0/D6'].partnumber}</td><td>${machine.dimm['P0/D6'].serialnumber}</td><td><a href="editmdimm?id=P0/D6">edit</a></td></tr>
    	<tr><td>P0/D7</td><td>${machine.dimm['P0/D7'].partnumber}</td><td>${machine.dimm['P0/D7'].serialnumber}</td><td><a href="editmdimm?id=P0/D7">edit</a></td></tr>
    	<tr><td>P0/D8</td><td>${machine.dimm['P0/D8'].partnumber}</td><td>${machine.dimm['P0/D8'].serialnumber}</td><td><a href="editmdimm?id=P0/D8">edit</a></td></tr>
    	<tr><td>P0/D9</td><td>${machine.dimm['P0/D9'].partnumber}</td><td>${machine.dimm['P0/D9'].serialnumber}</td><td><a href="editmdimm?id=P0/D9">edit</a></td></tr>
    	<tr><td>P0/D10</td><td>${machine.dimm['P0/D10'].partnumber}</td><td>${machine.dimm['P0/D10'].serialnumber}</td><td><a href="editmdimm?id=P0/D10">edit</a></td></tr>
    	<tr><td>P0/D11</td><td>${machine.dimm['P0/D11'].partnumber}</td><td>${machine.dimm['P0/D11'].serialnumber}</td><td><a href="editmdimm?id=P0/D11">edit</a></td></tr>
   </table>
   <table class="col-md-5 dimm table-bordered table-hover" style="margin:30px" align='center' border='1' cellspacing='0'>
    	<tr><td>Location</td><td>Part Number</td><td>Serial Number</td><td>Action</td></tr>
    	<tr><td>P1/D0</td><td>${machine.dimm['P1/D0'].partnumber}</td><td>${machine.dimm['P1/D0'].serialnumber}</td><td><a href="editmdimm?id=P1/D0">edit</a></td></tr>
    	<tr><td>P1/D1</td><td>${machine.dimm['P1/D1'].partnumber}</td><td>${machine.dimm['P1/D1'].serialnumber}</td><td><a href="editmdimm?id=P1/D1">edit</a></td></tr>
    	<tr><td>P1/D2</td><td>${machine.dimm['P1/D2'].partnumber}</td><td>${machine.dimm['P1/D2'].serialnumber}</td><td><a href="editmdimm?id=P1/D2">edit</a></td></tr>
    	<tr><td>P1/D3</td><td>${machine.dimm['P1/D3'].partnumber}</td><td>${machine.dimm['P1/D3'].serialnumber}</td><td><a href="editmdimm?id=P1/D3">edit</a></td></tr>
    	<tr><td>P1/D4</td><td>${machine.dimm['P1/D4'].partnumber}</td><td>${machine.dimm['P1/D4'].serialnumber}</td><td><a href="editmdimm?id=P1/D4">edit</a></td></tr>
    	<tr><td>P1/D5</td><td>${machine.dimm['P1/D5'].partnumber}</td><td>${machine.dimm['P1/D5'].serialnumber}</td><td><a href="editmdimm?id=P1/D5">edit</a></td></tr>
    	<tr><td>P1/D6</td><td>${machine.dimm['P1/D6'].partnumber}</td><td>${machine.dimm['P1/D6'].serialnumber}</td><td><a href="editmdimm?id=P1/D6">edit</a></td></tr>
    	<tr><td>P1/D7</td><td>${machine.dimm['P1/D7'].partnumber}</td><td>${machine.dimm['P1/D7'].serialnumber}</td><td><a href="editmdimm?id=P1/D7">edit</a></td></tr>
    	<tr><td>P1/D8</td><td>${machine.dimm['P1/D8'].partnumber}</td><td>${machine.dimm['P1/D8'].serialnumber}</td><td><a href="editmdimm?id=P1/D8">edit</a></td></tr>
    	<tr><td>P1/D9</td><td>${machine.dimm['P1/D9'].partnumber}</td><td>${machine.dimm['P1/D9'].serialnumber}</td><td><a href="editmdimm?id=P1/D9">edit</a></td></tr>
    	<tr><td>P1/D10</td><td>${machine.dimm['P1/D10'].partnumber}</td><td>${machine.dimm['P1/D10'].serialnumber}</td><td><a href="editmdimm?id=P1/D10">edit</a></td></tr>
    	<tr><td>P1/D11</td><td>${machine.dimm['P1/D11'].partnumber}</td><td>${machine.dimm['P1/D11'].serialnumber}</td><td><a href="editmdimm?id=P1/D11">edit</a></td></tr>

</table>
</div>
<div class="row">
<h2>PCIe</h2>
</div>

<table style="width:100%; margin:0 auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
    	<tr><td>Location</td><td>Name</td><td>Part Number</td><td>Action</td>
    	<tr><td>Slot 1</td><td>${machine.pcie.slot1.nickname }</td><td>${machine.pcie.slot1.partnumber }</td><td><a href="editmpci?id=slot1">edit</a></td></tr>
		<tr><td>Slot 2</td><td>${machine.pcie.slot2.nickname }</td><td>${machine.pcie.slot2.partnumber }</td><td><a href="editmpci?id=slot2">edit</a></td></tr> 
		<tr><td>Slot 3</td><td>${machine.pcie.slot3.nickname }</td><td>${machine.pcie.slot3.partnumber }</td><td><a href="editmpci?id=slot3">edit</a></td></tr>
		<tr><td>Slot 4</td><td>${machine.pcie.slot4.nickname }</td><td>${machine.pcie.slot4.partnumber }</td><td><a href="editmpci?id=slot4">edit</a></td></tr>
		<tr><td>Slot 5</td><td>${machine.pcie.slot5.nickname }</td><td>${machine.pcie.slot5.partnumber }</td><td><a href="editmpci?id=slot5">edit</a></td></tr>
		<tr><td>Slot 6</td><td>${machine.pcie.slot6.nickname }</td><td>${machine.pcie.slot6.partnumber }</td><td><a href="editmpci?id=slot6">edit</a></td></tr>
</table>
<nav>
<ul class="pager">
<li><a href="save">Save</a></li>
<li><a href="update">Update</a></li>
</ul>
</nav>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-body">
        	<div class='load'>
            <img src="image/load.gif"></img>
            </div>
        </div>
    </div><!-- /.modal -->
</div>
</body>
<script src="js/vue.js"></script>
<script src="js/machine.js"></script>
<link href="css/machine.css" rel="stylesheet" type="text/css"></link>
</html>