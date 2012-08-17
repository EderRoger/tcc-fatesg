<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	

<!DOCTYPE html>
<html lang="pt-br">

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
	<div data-role="page" id="frmInsutria" data-add-back-btn="true">
		<header data-role="header">
			<h2>Cadastro</h2>
		</header>
		<section data-role="content">
			<form action="servletmain" name="frmIndustria" method="post">
				<div>
					<input type="hidden" name="business" value="IndustriaAction" />
                <input type="hidden" name="acao" value="salvar" />
                <input type="hidden" name="id" value="${objIndustria.id}" />
                
				<label for="nome">Razão Social:</label> <input type="text" name="nome" placeholder="preenchimento obrigatório" value="${objIndustria.nome}" required/>
				<label for="cnpj">CNPJ:</label> <input type="number" name="cnpj" value="${objIndustria.cnpj}" /> 
				<label for="endereco">Endereço:</label> <input type="text" name="endereco" value="${objIndustria.endereco}" />
				<label for="email">E-mail:</label> <input type="email" name="email" placeholder="preenchimento obrigatório" value="${objIndustria.email}" required/>
				<label for="senha">Senha:</label> <input type="password" placeholder="preenchimento obrigatório" name="senha" required/> 
				<label for="txtTelefone">Telefone:</label> <input type="tel" name="txtTelefone" value="${industria.telefone}" />
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