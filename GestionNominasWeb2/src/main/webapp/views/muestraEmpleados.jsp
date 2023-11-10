<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Empleado"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mostrar Empleados</title>

    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


    <div class="container">
        <h1>Empleados</h1>
        <table class="table table-bordered">
            <tr>
                <th>Nombre</th>
                <th>DNIs</th>
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años trabajados</th>
            </tr>
            <c:forEach var="empleado" items="${empleados}">
                <tr>
                    <td>${empleado.nombre}</td>
                    <td>${empleado.dni}</td>
                    <td>${empleado.sexo}</td>
                    <td>${empleado.categoria}</td>
                    <td>${empleado.anyos}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.jsp" class="btn btn-primary">Volver a menú</a>
    </div>

    <!-- Include Bootstrap JavaScript (optional, for certain features) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>

