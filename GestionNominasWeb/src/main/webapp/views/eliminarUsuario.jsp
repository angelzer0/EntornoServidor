<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar Usuario</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Eliminar Empleados</h1>

        <%-- Agregar este bloque para mostrar el mensaje --%>
        <% String mensaje = (String)request.getAttribute("mensaje"); %>
        <% if (mensaje != null) { %>
            <div class="alert alert-success" role="alert">
                <%= mensaje %>
            </div>
        <% } %>

        <form action="NominasController" method="post">
            <input type="hidden" name="opcion" value="eliminarUsuario">
            <div class="mb-3">
                <label for="dniEliminar" class="form-label">DNI del empleado a eliminar:</label>
                <input type="text" class="form-control" id="dniEliminar" name="dniEliminar" required pattern="[0-9]{8}[A-Za-z]" title="Debe tener 8 números seguidos de una letra" required>
            </div>
            <button type="submit" class="btn btn-danger">Eliminar Empleado</button>
        </form>
        <a href="index.jsp" class="btn btn-secondary mt-2">Volver a menú</a>
    </div>

    <!-- Include Bootstrap JavaScript (optional, for certain features) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>

