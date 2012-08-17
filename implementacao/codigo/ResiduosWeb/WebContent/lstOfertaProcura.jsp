<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-br">
<%@include file="acessonegado.jsp" %>
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Lista de Oferta/Procura</title>
<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" id="lstOfertaProcura" data-add-back-btn="true">
		<header data-role="header">
			<h2>Oferta/Procura Cadastrados</h2>
		</header>
		<nav data-role="navbar">
			<ul>
				<li><a href="index.jsp"  data-icon="home" >Home</a></li>
				<c:if test="${objUsuarioSS.tipoUsuario==0}">
					<li><a href="cadastros.jsp" data-icon="plus" >Cadastros</a></li>
				</c:if>
				<li><a href="relatorios.jsp" data-icon="search" class="ui-btn-active ui-state-persist" >Consultas</a></li>
				<li><a href="servletmain?business=AutenticacaoAction&acao=logout" data-icon="back" >Logout</a></li>
			</ul>
		</nav>
		<section data-role="content">
		<p style="color:red; margin-bottom:20px;">${msg}</p>
		
			<ul data-role="listview" data-filter="true" data-inset="true" data-split-icon="delete">
			<li data-role="list-divider">Ofertas<span class="ui-li-count">Qtd:&nbsp;${qtdOfertas}</span></li>
				<c:forEach items="${lstOferta}" var="item">
					<li><a href="servletmain?business=OfertaProcuraAction&acao=editar&id=${item.id}">
							<p><h3>${item.residuo.descricao}  </h3></p>
							<p>Industria: <strong>${item.industria.nome}</strong></p>
							<p class="ui-li-aside">R$ <strong>${item.preco}</strong></p>
							<span class="ui-li-count">Qtd:&nbsp;${item.quantidade}</span>
							<a href="servletmain?business=OfertaProcuraAction&acao=excluir&id=${item.id}"></a>
					</a></li>
				</c:forEach>
				</li>
				
			<li data-role="list-divider">Procuras<span class="ui-li-count">Qtd:&nbsp;${qtdProcuras}</span></li>
				<c:forEach items="${lstProcura}" var="item">
					<li><a href="servletmain?business=OfertaProcuraAction&acao=editar&id=${item.id}">
							<p><h3>${item.residuo.descricao}  </h3></p>
							<p>Industria: <strong>${item.industria.nome}</strong></p>
							<p class="ui-li-aside">R$ <strong>${item.preco}</strong></p>
							<span class="ui-li-count"> Qtd:&nbsp;${item.quantidade}</span>
							<a href="servletmain?business=OfertaProcuraAction&acao=excluir&id=${item.id}"></a>
					</a></li>
				</c:forEach>
				</li>
				</ul>
		</section>
		<footer data-role="footer" data-position="fixed">
			<nav data-role="navbar">
				<ul>
					<li><a href="#">Institucional</a></li>
					<li><a href="#">Contato</a></li>
					<li><a href="#">Ajuda</a></li>
				</ul>
			</nav>
		</footer>

	</div>
</body>
</html>