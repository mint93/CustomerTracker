<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List customers</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relational Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
		<input type="button" value="Save Customer"
		 	   onClick="window.location.href='showFormForAdd'; return false"
		 	   class="add-button" />
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="customer" items="${customers}">
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${customer.id}"></c:param>
				</c:url>
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${customer.id}"></c:param>
				</c:url>
					<tr>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}" onClick="if(!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>