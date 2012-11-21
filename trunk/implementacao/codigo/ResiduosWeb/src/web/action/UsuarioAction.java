/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.controller.BusinessLogic;
import web.util.CriptografiaMD5;
import web.util.PopulateObject;
import fatesg.projeto.integrador.dao.AbstractDAO;
import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.Usuario;

/**
 * 
 * @author Professor
 */
public class UsuarioAction implements BusinessLogic {

	AbstractDAO<Usuario> abstractUsuarioDao = lookupAbstractDaoLocal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		try {
			if (acao.equals("novo")) {
				request.setAttribute("objUsuario", new Usuario());
				request.getRequestDispatcher("frmUsuario.jsp").forward(request, response);
				return;
			}
			if (acao.equals("listar")) {
				Usuario user = (Usuario) request.getSession().getAttribute("objUsuarioSS");
				Industria industria = lookupIndustriaLocal().obter(user.getIndustria().getId(), Industria.class);

				request.setAttribute("lstUsuarios", abstractUsuarioDao.obterUsuarios(industria));
				request.getRequestDispatcher("lstUsuarios.jsp").forward(request, response);
				return;
			}
			if (acao.equals("salvar")) {
				save(request, response);
				request.setAttribute("msg", "Operacao Realizada");
				request.setAttribute("objUsuario", new Usuario());
				Usuario user = (Usuario) request.getSession().getAttribute("objUsuarioSS");
				Industria industria = lookupIndustriaLocal().obter(user.getIndustria().getId(), Industria.class);

				request.setAttribute("lstUsuarios", abstractUsuarioDao.obterUsuarios(industria));
				request.getRequestDispatcher("lstUsuarios.jsp").forward(request, response);
				return;
			}
			if (acao.equals("excluir")) {
				Long id = Long.parseLong(request.getParameter("id"));
				abstractUsuarioDao.remover(abstractUsuarioDao.obter(id, Usuario.class));
				Usuario user = (Usuario) request.getSession().getAttribute("objUsuarioSS");
				Industria industria = lookupIndustriaLocal().obter(user.getIndustria().getId(), Industria.class);

				request.setAttribute("lstUsuarios", abstractUsuarioDao.obterUsuarios(industria));
				request.getRequestDispatcher("lstUsuarios.jsp").forward(request, response);
				return;
			}
			if (acao.equals("editar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				request.setAttribute("objUsuario", abstractUsuarioDao.obter(id, Usuario.class));
				request.getRequestDispatcher("frmUsuario.jsp").forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String validarCampos(Usuario usuario) {
		String erros = "";
		if (usuario == null || usuario.getNome().length() < 1) {
			erros += "- Nome é obrigatório.<br/>";
		}
		if (usuario == null || usuario.getLogin().length() < 1) {
			erros += "- Email é obrigatório.<br/>";
		}
		if (usuario == null || (usuario.getSenha() == null && usuario.getId() == null)) {
			erros += "- Senha é obrigatório.<br/>";
		}

		return erros;
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		long idIndustria = Long.parseLong(request.getParameter("id_industria"));
		Industria industria = lookupIndustriaLocal().obter(idIndustria, Industria.class);
		Usuario usuario = (Usuario) PopulateObject.createObjectBusiness(new Usuario(), request);

		Long cnpj = ((request.getParameter("cnpj") != null) ? Long.parseLong(request.getParameter("cnpj").replace(".", "").replace("/", "").trim())
				: 0);
		industria.setCnpj(cnpj);
		industria.setEndereco(request.getParameter("endereco"));
		industria.setNome(request.getParameter("nome"));
		industria.setTelefone(request.getParameter("txtTelefone"));

		usuario.setIndustria(industria);
		if (usuario.getId() == 0) {
			usuario.setSenha(CriptografiaMD5.encrypt(usuario.getSenha()));
		} else {
			Usuario user = abstractUsuarioDao.obter(usuario.getId(), Usuario.class);
			usuario.setSenha(user.getSenha());
		}
		try {
			String erros = validarCampos(usuario);
			if (erros.length() > 0) {
				request.setAttribute("msg", erros);
				request.getRequestDispatcher("frmUsuario.jsp").forward(request, response);
			}
			abstractUsuarioDao.salvar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Usuario> lookupAbstractDaoLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<Usuario>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);
		}
	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Industria> lookupIndustriaLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<Industria>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);
		}
	}

}
