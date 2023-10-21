<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Empleado"%>
<%@ page import="model.Nomina"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Opción 2 Menú</title>

<!-- Include Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-4">
		<p><%=request.getAttribute("mensaje")%></p>
		<h1 class="display-4">Mostrar Sueldos</h1>
		<table class="table table-bordered">
			<tr>
				<th>Nombre</th>
				<th>DNI</th>
				<th>Sueldo</th>
			</tr>
			<tr>
				<td><c:out value="${requestScope.empleadoNombre}" /></td>
				<td><c:out value="${requestScope.empleadoDni}" /></td>
				<td><c:out value="${requestScope.sueldo}" /></td>
			</tr>
		</table>
		<a href="index.jsp" class="btn btn-primary">Volver a menú</a>
	</div>

	<!-- Include Bootstrap JavaScript (optional, for certain features) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
