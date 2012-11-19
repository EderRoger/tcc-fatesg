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
import fatesg.projeto.integrador.entidade.TipoUsuario;
import fatesg.projeto.integrador.entidade.Usuario;

/**
 * 
 * @author Professor
 */
public class IndustriaAction implements BusinessLogic {

	AbstractDAO<Industria> abstractIndustriaDao = lookupAbstractDaoLocal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		try {
			if (acao.equals("novo")) {
				request.setAttribute("objIndustria", new Industria());
				request.getRequestDispatcher("frmIndustria.jsp").forward(
						request, response);
				return;
			}
			if (acao.equals("listar")) {
				request.setAttribute("lstIndustria",
						abstractIndustriaDao.listar("Industria"));
				request.getRequestDispatcher("lstIndustria.jsp").forward(
						request, response);
				return;
			}
			if (acao.equals("salvar")) {
				save(request, response);
				request.setAttribute("msg", "Operacao Realizada");
				request.setAttribute("objIndustria", new Industria());
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				return;
			}
			if (acao.equals("excluir")) {
				Long id = Long.parseLong(request.getParameter("id"));
				abstractIndustriaDao.remover(abstractIndustriaDao.obter(id,
						Industria.class));
				request.setAttribute("lstIndustria",
						abstractIndustriaDao.listar("Industria"));
				request.getRequestDispatcher("lstIndustria.jsp").forward(
						request, response);
				return;
			}
			if (acao.equals("editar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				request.setAttribute("objIndustriaResiduo",
						abstractIndustriaDao.obter(id, Industria.class));
				request.getRequestDispatcher("frmIndustria.jsp").forward(
						request, response);
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

	private String validarCampos(Industria industria, Usuario usuario) {
		String erros = "";
		if (industria.getNome().length() < 1) {
			erros += "- Razão Social é obrigatório.<br/>";
		}
		if (industria.getEmail().length() < 1) {
			erros += "- Email é obrigatório.<br/>";
		}
		if (usuario == null || usuario.getSenha() == null || usuario.getSenha().length() < 1) {
			erros += "- Senha é obrigatório.<br/>";
		}

		return erros;
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {

		Industria industria = (Industria) PopulateObject.createObjectBusiness(
				new Industria(), request);
		String senha = request.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setNome(industria.getNome());
		usuario.setLogin(industria.getEmail());
		if (!senha.toString().equals(""))
			usuario.setSenha(CriptografiaMD5.encrypt(senha));
		usuario.setStatus(true);
		usuario.setTipoUsuario(1);
		usuario.setIndustria(industria);

		try {

			String erros = validarCampos(industria, usuario);
			if (erros.length() > 0) {
				request.setAttribute("msg", erros);
				request.getRequestDispatcher("frmIndustria.jsp").forward(
						request, response);
			}

			lookupUsuarioLocal().salvar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Industria> lookupAbstractDaoLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<Industria>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,
					"exception caught", ne);
			throw new RuntimeException(ne);
		}
	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Usuario> lookupUsuarioLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<Usuario>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,
					"exception caught", ne);
			throw new RuntimeException(ne);
		}
	}

}
