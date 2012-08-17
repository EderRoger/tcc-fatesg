<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Página Expirada</title>
<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" data-theme="a" id="expira">
		<header data-role="header">
		<a href="login.jsp" data-icon="home" data-iconpos="notext" data-direction="reverse">Home</a>
			<h2>Página Expirada</h2>
		</header>
		<section data-role="content">
			<h1>Página Expirada!</h1>
			<p>A pagina expirou, clique no Ícone 'Home'</p>
		</section>
	</div>
</body>
</html>