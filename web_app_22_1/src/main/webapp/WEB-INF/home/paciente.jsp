<%--
  Created by IntelliJ IDEA.
  User: crist
  Date: 17/05/2022
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>PACIENTE</title>
    </head>
    <body>
        <h1> Usuário: ${usuario_logado.emailUsuario}</h1>

        <div>
            <h1>PACIENTE</h1>
            <form action="paciente-controller" method="post">
                <c:choose>
                    <c:when test="${p.idPaciente == 0}">
                        <h1>CADASTRAR PACIENTE</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>EDITAR PACIENTE</h1>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text"
                           class="form-control"
                           id="nome"
                           name="nome"
                           value="${p.nomePaciente}"
                           placeholder="Nome do paciente"
                           required>
                </div>
                <div class="form-group">
                    <label for="idade">Idade:</label>
                    <input type="number"
                           class="form-control"
                           id="idade"
                           name="idade"
                           placeholder="Idade do paciente"
                           value="${p.idade}"
                           required>
                </div>
                <%-- TODO alterar o input para radio--%>
                <div class="form-group">
                    <label>Sexo: </label>
                    <label for="masculino">
                        <input type="radio"
                               class="form-control"
                               id="masculino"
                               name="sexo"
                               value="Masculino"
                               required>
                        Masculino
                    </label>
                    <label for="feminino">
                        <input type="radio"
                               class="form-control"
                               id="feminino"
                               name="sexo"
                               value="Feminino">
                        Feminino
                    </label>
<%--                    <input type="text" class="form-control" id="sexo" name="sexo" placeholder="Sexo do paciente">--%>
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone:</label>
                    <input type="text"
                           class="form-control"
                           id="telefone"
                           name="telefone"
                           value="${p.telefone}"
                           placeholder="Telefone para contato"
                           required>
                </div>
                <div class="form-group">
                    <label for="cpf">CPF:</label>
                    <input type="text"
                           class="form-control"
                           id="cpf"
                           name="cpf"
                           value="${p.CPF}"
                           placeholder="CPF do paciente"
                           required>
                </div>
                <input type="hidden" name="idPaciente" value="${p.idPaciente}">
                <c:choose>
                    <c:when test="${p.idPaciente == null}">
                        <button type="submit"
                                class="btn btn-primary"
                                name="opcao"
                                value="cadastrar">
                            Cadastrar
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit"
                                class="btn btn-primary"
                                name="opcao"
                                value="atualizar">
                            Editar
                        </button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>

        <c:if test="${not empty mensagem}">
            <h3>${mensagem}</h3>
        </c:if>

        <c:if test="${not empty pacientes}">
            <div>
                <table>
                    <thead>
                    <tr>
<%--                        <th>ID</th>--%>
                        <th>Nome</th>
                        <th>Idade</th>
                        <th>Sexo</th>
                        <th>Telefone</th>
                        <th>CPF</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${pacientes}">
                        <tr>
<%--                            <td>${p.idPaciente}</td>--%>
                            <td>${p.nomePaciente}</td>
                            <td>${p.idade}</td>
                            <td>${p.sexo}</td>
                            <td>${p.telefone}</td>
                            <td>${p.CPF}</td>
                            <td> <%-- http://localhost:8080/web_app_22_1/ --%>
                                <a href="<c:url value="paciente-controller?opcao=excluir&&idPaciente=${p.idPaciente}"/>">Excluir</a>
                                <a href="<c:url value="paciente-controller?opcao=editar&&idPaciente=${p.idPaciente}"/>">Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <a href="controlador?opcao=">VOLTAR</a>
    </body>
</html>
