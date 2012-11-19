<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<%@include file="acessonegado.jsp" %>
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Container de Resíduos</title>
<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" id="cadastros">
		<header data-role="header">
			<h2>Cadastros</h2>
		</header>
		<nav data-role="navbar">
			<ul>
				<li><a href="index.jsp"  data-icon="home" >Home</a></li>
				<c:if test="${objUsuarioSS.tipoUsuario==0}">
					<li><a href="cadastros.jsp" data-icon="plus" class="ui-btn-active ui-state-persist" >Cadastros</a></li>
				</c:if>
				<li><a href="relatorios.jsp" data-icon="search" >Consultas</a></li>
				<li><a href="servletmain?business=AutenticacaoAction&acao=logout" data-icon="back" >Logout</a></li>
			</ul>
		</nav>
		<section data-role="content">
		    <c:if test="${objUsuarioSS.tipoUsuario==0}">
			<a href="servletmain?business=CategoriaAction&acao=novo" data-role="button">Categoria Resíduo</a>
			</c:if>
			<a href="servletmain?business=UsuarioAction&acao=novo" data-role="button">Usuario</a>
			<a href="servletmain?business=ResiduoAction&acao=novo" data-role="button">Resíduo</a>
			<a href="servletmain?business=OfertaProcuraAction&acao=novo" data-role="button">Oferta Procura</a>
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
