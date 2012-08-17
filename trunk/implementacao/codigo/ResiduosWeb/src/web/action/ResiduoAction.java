/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.controller.BusinessLogic;
import web.util.PopulateObject;
import fatesg.projeto.integrador.dao.AbstractDAO;
import fatesg.projeto.integrador.entidade.Categoria;
import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.Residuo;

/**
 * 
 * @author Professor
 */
public class ResiduoAction implements BusinessLogic {

	AbstractDAO<Residuo> abstractResiduoDao = lookupAbstractDaoLocal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		try {
			if (acao.equals("novo")) {
				request.setAttribute("objResiduo", new Residuo());
				request.setAttribute("lstCategoriaResiduo",
						abstractResiduoDao.listar("Categoria"));
				request.getRequestDispatcher("frmResiduo.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("listar")) {
				request.setAttribute("lstResiduos",
						abstractResiduoDao.listar("Residuo"));
				request.getRequestDispatcher("lstResiduos.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("salvar")) {
				save(request);
				request.setAttribute("msg", "Operacao Realizada");
				request.setAttribute("objResiduo", new Residuo());
				request.setAttribute("lstResiduos",
						abstractResiduoDao.listar("Residuo"));
				request.getRequestDispatcher("lstResiduos.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("excluir")) {
				Long id = Long.parseLong(request.getParameter("id"));
				abstractResiduoDao.remover(abstractResiduoDao.obter(id,Residuo.class));
				request.setAttribute("lstResiduos",
						abstractResiduoDao.listar("Residuo"));
				request.getRequestDispatcher("lstResiduos.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("editar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				request.setAttribute("objResiduo",
						abstractResiduoDao.obter(id,Residuo.class));
				request.setAttribute("lstCategoriaResiduo",
						abstractResiduoDao.listar("Categoria"));
				request.getRequestDispatcher("frmResiduo.jsp")
						.forward(request, response);
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

	private void save(HttpServletRequest request) {
		long idCategoria = Long.parseLong(request.getParameter("id_categoria"));
		Categoria categoria = lookupCategoriaLocal().obter(idCategoria,Categoria.class);
		Residuo residuo = (Residuo) PopulateObject.createObjectBusiness(
				new Residuo(), request);
		residuo.setCategoria(categoria);
		try {

			abstractResiduoDao.salvar(residuo);
		}
		catch (Exception e) {
			request.setAttribute("msg", "Ocorreu um erro, talvez você esteja tentando excluir um tipo residuo que está sendo usado em um residuo.");
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Residuo> lookupAbstractDaoLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<Residuo>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,
					"exception caught", ne);
			throw new RuntimeException(ne);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Categoria> lookupCategoriaLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<Categoria>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,
					"exception caught", ne);
			throw new RuntimeException(ne);
		}
	}

}
