<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Editar Cadastro</title>
</head>
<body>
    <form action="cadastro-controller" method="get">
        Nome: <input type="text" name="nome" value="${p.nomePaciente}" /> <br/>
        Idade: <input type="number" name="idade" value=" ${p.idade}" /> <br/>
        Sexo: <input type="text" nome="sexo" value="${p.sexo}" /> <br/>
        Telefone: <input type="text" name="telefone" value="${p.telefone}"/>
        CPF: <input type="text" name="cpf" value="${p.CPF}" />
        <button type="submit" class="btn btn-primary" value="cadastrar" name="cadastrar">Cadastrar</button>
    </form>
    <a href="controlador?opcao=">VOLTAR</a>
</body>
</html>
