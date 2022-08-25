<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 08/05/2022
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Dashboard</title>
    </head>
    <body>
        <h1> Usuário: ${usuario_logado.emailUsuario}</h1>
        <ul>
<%--            <li>--%>
<%--                <a href="controlador?opcao=operador">CADASTRAR OPERADORES</a>--%>
<%--            </li>--%>

            <li>
                <a href="controlador?opcao=paciente">PACIENTE</a>
            </li>

            <li>
                <a href="controlador?opcao=atendimento">ATENDIMENTO</a>
            </li>
            <li>
                <a href="controlador?opcao=internacao">INTERNACAO</a>
            </li>

            <li>
                <a href="controlador?opcao=logout">[Logout]</a>
            </li>
        </ul>

    </body>
</html>
