<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Employee Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/employee.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1> Employee Management</h1>
				<form action="Employee.jsp" id="formEmployee" name="formEmployee" method="post">
					Employee Name:
					<input id="empName" name="empName" type="text" class="form-control form-control-sm">
					<br>
					Employee Address:
					<input id="empAddress" name="empAddress" type="text" class="form-control form-control-sm">
					<br>
					Employee Email:
					<input id="empEmail" name="empEmail" type="text" class="form-control form-control-sm">
					<br>
					Employee Contact No:
					<input id="empContactno" name="empContactno" type="text" class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					 <input type="hidden" id="hididSave" name="hididSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divEmployeeGrid">
					 <%
					 	Employee EmployeeObj = new Employee();
					 	out.print(EmployeeObj.readEmployee()); 
					 %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>