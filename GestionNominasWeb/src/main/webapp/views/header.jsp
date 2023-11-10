<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Nominas Web</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="NominasController?opcion=crearUsuario">Crear Empleado</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="NominasController?opcion=eliminarUsuario">Eliminar Empleado</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="NominasController?opcion=muestraEmpleados">Mostrar los datos de los empleados</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="NominasController?opcion=muestraSalario">Mostrar el salario de un empleado</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="NominasController?opcion=modificaEmpleado">Modificar los datos de un empleado</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>