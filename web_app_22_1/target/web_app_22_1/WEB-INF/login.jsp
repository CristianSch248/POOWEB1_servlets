<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 08/05/2022
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login do sistema</h1>
        <form action="login" method="post">
            <label for="email"><b>E-mail</b></label>
            <input type="text" class="form-control" id="email" name="email" placeholder="E-mail">
            <label for="senha"><b>Senha</b></label>
            <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha">
            <button type="submit" value="login" name="login">Entrar</button>
        </form>
        <c:if test="${not empty erro}">
            <ha2>
            <b>${erro}</b>
            </ha2>
        </c:if>
    </body>
</html>