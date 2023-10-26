<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Datos del Empleado</title>

<!-- Include Bootstrap CSS from a CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container my-5">
		<h1 class="display-4">Modificar Datos del Empleado</h1>

		<form action="NominasController" method="post">
			<input type="hidden" name="opcion" value="modificarEmpleado">

			<div class="form-group">
				<label for="nuevoNombre">Nuevo Nombre:</label> <input type="text"
					name="nuevoNombre" class="form-control" id="nuevoNombre"
					placeholder="Ingrese el nuevo nombre" required>
			</div>
			<br>


			<div class="form-group">
				<label for="nuevoSexo">Nuevo Sexo:</label> <input type="text"
					name="nuevoSexo" class="form-control" id="nuevoSexo"
					placeholder="Ingrese el nuevo sexo" required>
			</div>
			<br>

			<div class="form-group">
				<label for="dni">DNI del Empleado:</label> <input type="text"
					name="dni" class="form-control" id="dni"
					placeholder="Ingrese el DNI del Empleado" required>
			</div>
			<br>

			<div class="form-group">
				<label for="nuevaCategoria">Nueva Categoría:</label> <input
					type="number" name="nuevaCategoria" class="form-control"
					id="nuevaCategoria" placeholder="Ingrese la nueva categoría"
					required>
			</div>
			<br>

			<div class="form-group">
				<label for="nuevosAnyos">Nuevos Años Trabajados:</label> <input
					type="number" name="nuevosAnyos" class="form-control"
					id="nuevosAnyos" placeholder="Ingrese los nuevos años trabajados"
					required>
			</div>
			<br>

			<button type="submit" class="btn btn-primary">Modificar
				Empleado</button>
		</form>

		<a href="index.jsp" class="btn btn-secondary mt-3">Volver a
			men&uacute;</a>
	</div>

	<!-- Include Bootstrap JavaScript (optional, for certain features) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
