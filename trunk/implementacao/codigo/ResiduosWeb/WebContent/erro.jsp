<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Erro</title>
<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" data-theme="a" id="expira">
		<header data-role="header">
		<a href="login.jsp" data-icon="home" data-iconpos="notext" data-direction="reverse">Home</a>
			<h2>Ocorreu um erro!</h2>
		</header>
		<section data-role="content">
			<p>Não foi desta vez, tente mais tarde! :(</p>
			
			<p><b>Clique no icone Home, que encontra-se no canto superior esquerdo.</b></p>
		</section>
	</div>
</body>
</html>