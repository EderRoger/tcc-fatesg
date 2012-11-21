<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div data-role="page" id="index">
		<header data-role="header">
			<h2>Container de Resíduos Mobile</h2>
		</header>
		<%@include file="menu.jsp" %>
		<section data-role="content">
			<h1>Ajuda</h1>
			<p>Navegue pelo menu interativo.</p>
		</section>
		<%@include file="rodape.jsp" %>

	</div>
</body>
</html>
