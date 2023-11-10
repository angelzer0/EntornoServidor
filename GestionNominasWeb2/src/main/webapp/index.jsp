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
	<jsp:include page="views/header.jsp" />

	<% if(request.getAttribute("content") == null || ((String) request.getAttribute("content")).isEmpty()){ %>
            <jsp:include page="/views/bienvenido.jsp" />
        <%}else{%>
             <jsp:include page='<%= (String) request.getAttribute("content") %>' />
        <%}%>

	<jsp:include page="views/footer.jsp" />

	<!-- Include Bootstrap JavaScript (optional, for certain features) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>



