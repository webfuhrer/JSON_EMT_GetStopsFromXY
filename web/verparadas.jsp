<%-- 
    Document   : verparadas
    Created on : 15-dic-2017, 13:08:12
    Author     : luis
--%>

<%@page import="paqueteemt.CreaHTML"%>
<%@page import="paqueteemt.ObjetoParada"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    ArrayList<ObjetoParada> lista_paradas=(ArrayList<ObjetoParada>) request.getAttribute("lista_paradas");
    String html=CreaHTML.crearTabla(lista_paradas);
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>PATRADAS:</h1>
        <%=html%>
    </body>
</html>
