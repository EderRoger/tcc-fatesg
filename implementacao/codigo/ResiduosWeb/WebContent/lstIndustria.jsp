<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-br">
<%@include file="acessonegado.jsp" %>
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Lista de Categorias</title>
<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" id="lstIndustrias" data-add-back-btn="true">
		<header data-role="header">
			<h2>Categorias Cadastrados</h2>
		</header>
		<%@include file="menu.jsp" %>
		<section data-role="content">
			<ul data-role="listview" data-filter="true" data-inset="true"
				data-split-icon="delete">
				<c:forEach items="${lstIndustria}" var="item">
					<li><a href="servletmain?business=IndustriaAction&acao=editar&id=${item.id}">
							<h2>
								<b>${item.nome}</b>
							</h2>
							<p>Email: ${item.email}</p>
							<p>Endereco: ${item.endereco}</p>
							<p>Telefone: ${item.telefone}</p>
							<a href="servletmain?business=IndustriaAction&acao=excluir&id=${item.id}"></a>
					</a></li>
				</c:forEach>
				</ul>
		</section>
		<%@include file="rodape.jsp" %>

	</div>
</body>
</html>