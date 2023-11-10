<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Usuario</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Crear Empleado</h1>
    <form action="NominasController" method="post">
        <input type="hidden" name="opcion" value="crearUsuario">
        <div class="mb-3">
            <label for="nuevoDni" class="form-label">DNI:</label>
            <input type="text" class="form-control" id="nuevoDni" name="nuevoDni" pattern="[0-9]{8}[A-Za-z]" title="Debe tener 8 números seguidos de una letra" required>
        </div>
        <div class="mb-3">
            <label for="nuevoNombre" class="form-label">Nombre:</label>
            <input type="text" class="form-control" id="nuevoNombre" name="nuevoNombre" required>
        </div>
        <div class="mb-3">
            <label for="nuevoSexo" class="form-label">Sexo (M/F):</label>
            <input type="text" class="form-control" id="nuevoSexo" name="nuevoSexo" pattern="[MF]" title="Debe ser 'M' o 'F'" required>
        </div>
        <div class="mb-3">
            <label for="nuevaCategoria" class="form-label">Categoría:</label>
            <input type="number" class="form-control" id="nuevaCategoria" name="nuevaCategoria" min="0" required>
        </div>
        <div class="mb-3">
            <label for="nuevosAnyos" class="form-label">Años de experiencia:</label>
            <input type="number" class="form-control" id="nuevosAnyos" name="nuevosAnyos" min="0" required>
        </div>
        <button type="submit" class="btn btn-primary">Crear Empleado</button>
    </form>
    <a href="index.jsp" class="btn btn-secondary mt-2">Volver a menú</a>
</div>

<!-- Include Bootstrap JavaScript (optional, for certain features) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>

