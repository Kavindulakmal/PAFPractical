<%@ page import="com.paf10.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Research Details</title>
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Items.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">


				<h1>Research Details Upload</h1>
				<form method='post' action='User.jsp' id='UserItem' name='UserItem'>
					Customer first Name: <input id='first_name' name='first_name' type='text' class='form-control col-md-3'><br>
					Customer last Name: <input id='last_name' name='last_name' type='text' class='form-control col-md-3'><br>
					Email: <input id='email' name='email' type='text' class='form-control col-md-3'><br>
					Address: <input id='address' name='address' type='text' class='form-control col-md-3'><br>
					Username: <input id='username' name='username' type='text' class='form-control col-md-3'><br>
					Password: <input id='password' name='password' type='text' class='form-control col-md-3'><br>
					<input id='btnSave' name='btnSave' type='button' value='Save' class='btn btn-primary'> 
					<input type='hidden' id='hidItemIDSave' name='hidItemIDSave' value=''>
				</form>

				<br>

				<div id='alertSuccess' name='alertSuccess' class='alert alert-success'></div>
				<div id='alertError' name='alertError' class='alert alert-danger'></div>

				<br>
				<div id="divItemsGrid">
				<%
				User itemObjRead = new User();
				System.out.print(itemObjRead.readUser());
				%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>