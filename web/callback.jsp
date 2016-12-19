
<%@page import="java.util.Map"%>
<%@page import="controller.index"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
    String name = request.getParameter("first_name");
    String correo = request.getParameter("correo");
    String gen = request.getParameter("gender");
        %>
        <h1>Llegue al callback!</h1>
        <h2>Nombre del Usuario: <%=name%> </h2>       
        <h3>Correo Electronico: <%=correo%></h3>
        <h4>Genero: <%=gen%></h4>
    </body>
</html>
