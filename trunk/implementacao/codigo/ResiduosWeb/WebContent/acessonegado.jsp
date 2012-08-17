<%@page import="fatesg.projeto.integrador.entidade.Usuario"%>
<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("objUsuarioSS");

    if (usuario == null) {
        response.sendRedirect("expira.jsp");
    } else if (usuario.getTipoUsuario() == 1) {
        response.sendRedirect("permissaonegada.jsp");
    }
%>