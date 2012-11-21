<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-br">
<%@include file="acessonegado.jsp" %>
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Container de Resíduos</title>
<link rel="stylesheet" href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" id="frmCategoriaResiduo" data-add-back-btn="true">
		<header data-role="header">
			<h2>Cadastro de Categorias de Resíduo</h2>
		</header>
		<%@include file="menu.jsp" %>
		<section data-role="content">
			<form action="servletmain" name="frmCategoriaResiduo" method="post">
				<div>
					<input type="hidden" name="business" value="CategoriaAction" /> <input
						type="hidden" name="acao" value="salvar" /> <input type="hidden"
						name="id" value="${objCategoriaResiduo.id}" /> <label for="descricao">Descrição:</label> <input type="text"
						name="descricao" value="${objCategoriaResiduo.descricao}" />



					<div data-role="controlgroup" data-type="horizontal"
						style="margin-top: 15px;">
						<input type="submit" name="btnSalvar" id="btnSalvar"
							data-icon="check" value="Salvar" /> <input type="reset"
							name="Limpar" id="Limpar" data-icon="delete" value="Limpar" />

					</div>
				</div>
			</form>
		</section>
		<%@include file="rodape.jsp" %>

	</div>
</body>
</html>