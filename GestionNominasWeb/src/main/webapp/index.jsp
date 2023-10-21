<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nominas Web</title>

<!-- Include Bootstrap CSS from a CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container text-center my-5">
		<h1>MEN&Uacute;</h1>
		<div class="list-group mt-4">
			<a href="NominasController?opcion=muestraEmpleados"
				class="list-group-item list-group-item-action">Mostrar los datos
				de los empleados</a> <a href="NominasController?opcion=muestraSalario"
				class="list-group-item list-group-item-action">Mostrar el
				salario de un empleado</a> <a
				href="NominasController?opcion=modificaEmpleado"
				class="list-group-item list-group-item-action">Modificar los
				datos de un empleado</a>
		</div>
	</div>

	<!-- Include Bootstrap JavaScript (optional, for certain features) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
