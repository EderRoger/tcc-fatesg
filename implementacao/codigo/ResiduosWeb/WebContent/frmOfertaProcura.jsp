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
<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" id="frmOfertaProcura" data-add-back-btn="true">
		<header data-role="header">
			<h2>Cadastro de Oferta/Procura</h2>
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
			<form action="servletmain" name="frmOfertaProcura" method="post">
				<div>
					<input type="hidden" name="business" value="OfertaProcuraAction" />
                <input type="hidden" name="acao" value="salvar" />
                <input type="hidden" name="id" value="${objOfertaProcura.id}" />
                <input type="hidden" name="id_industria" value="${objUsuarioSS.industria.id}" />
                
				<label for="id_residuo">Resíduo</label> <select
						name="id_residuo" id="id_residuo">
						<c:forEach items="${lstResiduo}" var="item">
							<option value="${item.id}" <c:if test="${item.id == objOfertaProcura.residuo.id}"> selected</c:if>>${item.descricao}</option>
						</c:forEach>
				</select>
				<label for="operacao">Operacao</label> <select name="operacao" id="operacao">
						<option value="1" <c:if test="${objOfertaProcura.operacao == 1}"> selected</c:if> >Venda</option>
                    	<option value="2" <c:if test="${objOfertaProcura.operacao == 2}"> selected</c:if> >Compra</option>
				</select>
				<label for="preco">Preco</label> <input type="number" name="preco" value="${objOfertaProcura.preco}" />
				<label for="quantidade">Quantidade</label> <input type="number" name="quantidade" value="${objOfertaProcura.quantidade}" />
					<p style="color:red; margin-top:20px;">${msg}</p>	
					<div data-role="controlgroup" data-type="horizontal">
						<input type="submit" name="btnSalvar" id="btnSalvar"
							data-icon="check" value="Salvar" /> <input type="reset"
							name="Limpar" id="Limpar" data-icon="delete" value="Limpar" />
					</div>
				</div>
			</form>
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