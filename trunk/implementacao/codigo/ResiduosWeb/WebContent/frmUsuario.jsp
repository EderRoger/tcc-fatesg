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
	<div data-role="page" id="frmUsuario" data-add-back-btn="true">
		<header data-role="header">
			<h2>Cadastro de Usuario</h2>
		</header>
		<%@include file="menu.jsp" %>
		<section data-role="content">
			<form action="servletmain" name="frmUsuario" method="post">
				<div>
					<input type="hidden" name="business" value="UsuarioAction" />
                <input type="hidden" name="acao" value="salvar" />
                <input type="hidden" name="id" value="${objUsuario.id}" />
                <input type="hidden" name="id_industria" value="${objUsuarioSS.industria.id}" />
                
                <div data-role="collapsible" data-theme="a" data-content-theme="a">
                <h3>Dados da Indústria</h3>
                <label for="nome">Razão Social:</label> <input type="text" name="nome" value="${objUsuario.industria.nome}" required/>
				<label for="cnpj">CNPJ:</label> <input type="number" name="cnpj" value="${objUsuario.industria.cnpj}" /> 
				<label for="endereco">Endereço:</label> <input type="text" name="endereco" value="${objUsuario.industria.endereco}" />
				<label for="txtTelefone">Telefone:</label> <input type="tel" name="txtTelefone" value="${objUsuario.industria.telefone}" />
                </div>
                
				<label for="nome">Nome</label> <input type="text" name="nome" value="${objUsuario.nome}" />
				<label for="login">Email:</label> <input type="text" name="login" value="${objUsuario.login}" />
				
				<label for="senha">Senha:</label> <input type="password" name="senha"  value="${objUsuario.senha}" <c:if test="${objUsuario.id != 0}">disabled="disabled"</c:if>  />
				
				<label for="tipoUsuario">Tipo</label> <select
						name="tipoUsuario" id="nivel">
						<option value="0" <c:if test="${objUsuario.tipoUsuario == 0}"> selected</c:if> >Administrador</option>
                    	<option value="1" <c:if test="${objUsuario.tipoUsuario == 1}"> selected</c:if> >Usuário</option>
				</select>
				<label for="status">Status</label> <select
						name="status" id="status">
						<option value="true" <c:if test="${objUsuario.status == true}"> selected</c:if> >Ativo</option>
                    	<option value="false" <c:if test="${objUsuario.status == false}"> selected</c:if> >Inativo</option>
				</select>
					<p style="color:red; margin-top:20px;">${msg}</p>	
					<div data-role="controlgroup" data-type="horizontal">
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