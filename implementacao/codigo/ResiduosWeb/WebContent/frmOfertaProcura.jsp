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
		<%@include file="menu.jsp" %>
		<section data-role="content">
			<form action="servletmain" name="frmOfertaProcura" method="post">
				<div>
					<input type="hidden" name="business" value="OfertaProcuraAction" />
                <input type="hidden" name="acao" value="salvar" />
                <input type="hidden" name="id" value="${objOfertaProcura.id}" />
                <input type="hidden" name="id_industria" value="${objUsuarioSS.industria.id}" />

               <div data-role="collapsible" data-theme="a" data-content-theme="a">
                <h3>Dados da Indústria</h3>
                <label for="nome">Razão Social:</label> <input type="text" name="nome" disabled="disabled"  value="${objOfertaProcura.industria.nome}"/>
                <label for="endereco">Endereço:</label> <input type="text" name="endereco" disabled="disabled" value="${objOfertaProcura.industria.endereco}" />
				<label for="email">E-mail:</label> <input type="email" name="email" disabled="disabled" value="${objOfertaProcura.industria.email}" required/>
				<label for="txtTelefone">Telefone:</label> <input type="tel" disabled="disabled" name="txtTelefone" value="${objOfertaProcura.industria.telefone}" />
               </div>
                
              

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
						<c:choose>
						    <c:when test="${objUsuarioSS.industria.id.equals(objOfertaProcura.industria.id)}">
						       <input type="submit" name="btnSalvar" id="btnSalvar" data-icon="check" value="Salvar" />
						       <input type="reset"  name="Limpar" id="Limpar" data-icon="delete" value="Limpar" />
						    </c:when>
						    <c:when test="${objOfertaProcura.industria.id == null}">
						        <input type="submit" name="btnSalvar" id="btnSalvar" data-icon="check" value="Salvar" />
						       <input type="reset"  name="Limpar" id="Limpar" data-icon="delete" value="Limpar" />
						    </c:when>
						    <c:otherwise>
						      
						    </c:otherwise>
						</c:choose>
					</div>
				</div>
			</form>
		</section>
		<%@include file="rodape.jsp" %>

	</div>
</body>
</html>