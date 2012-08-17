<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login - Container Resíduos</title>

<link rel="stylesheet"  href="css/themes/default/jquery.mobile.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/alteracoes_framework.js"></script>
<script src="js/jquery.mobile.js"></script>
</head>
<body>
	<div data-role="page" id="login">
		<header data-role="header">
			<h2>Login</h2>
		</header>
		<section data-role="content">
		<center><img  src="img/principal.png"/></center>
			<form action="servletmain" method="post">

				<input type="hidden" name="acao" value="logar" /> 
				<input type="hidden" name="business" value="AutenticacaoAction" />
				<label
					for="login">Email:</label> <input type="email" name="login" id="login" required/> <label
					for="senha">Senha:</label> <input type="password" name="senha" = id="senha" re/>
					<p style="color:red; margin-top:20px;">${msg}</p>		
				<div data-role="controlgroup" data-type="horizontal">
						<input type="submit" name="btn" id="btn"
							data-icon="check" value="Logar" /> 
			   </div>
			</form>
			<center><p style="width: 50%;"><a  href="servletmain?business=IndustriaAction&acao=novo" data-role="button" data-theme="b">Cadastre-se</a></p></center>
		</section>
		<footer data-role="footer" data-position="fixed">
			<p>
				<small>Container Resíduos - Eder, Kairo, Gleuvanir</small>
			</p>
		</footer>

	</div>
</body>
</html>
