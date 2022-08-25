<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 17/05/2022
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>CONSULTA</title>
    </head>
    <body>
        <h1> Usuário: ${operador_logado.emailUsuario}</h1>

        <h1> CONSULTA </h1>
        <a href="controlador?opcao=">VOLTAR</a>
    </body>
</html>
