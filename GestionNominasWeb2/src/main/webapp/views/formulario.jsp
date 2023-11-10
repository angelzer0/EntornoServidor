<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario DNI</title>

    <!-- Include Bootstrap CSS from a CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <p><%=request.getAttribute("mensaje")%></p>
    <div class="container mt-5">
        <h1>Formulario</h1>
        <form action="NominasController" method="post">
            <input type="hidden" name="opcion" value="buscar">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <tr>
                        <td>DNI del empleado:</td>
                        <td><input type="text" name="dni" class="form-control" style="width: 100%;" placeholder="Enter DNI"></td>
                    </tr>
                </table>
            </div>
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>
        <a href="index.jsp" class="btn btn-secondary mt-3">Volver a menú</a>
    </div>

    <!-- Include Bootstrap JavaScript (optional, for certain features) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
