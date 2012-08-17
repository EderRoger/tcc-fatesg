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
import web.util.PopulateObject;
import fatesg.projeto.integrador.dao.AbstractDAO;
import fatesg.projeto.integrador.entidade.Categoria;
import fatesg.projeto.integrador.entidade.Usuario;

/**
 * 
 * @author Professor
 */
public class CategoriaAction implements BusinessLogic {

	AbstractDAO<Categoria> abstractCategoriaDao = lookupAbstractDaoLocal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		try {
			if (acao.equals("novo")) {
				request.setAttribute("objCategoriaResiduo", new Categoria());
				request.getRequestDispatcher("frmCategoriaResiduo.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("listar")) {
				request.setAttribute("lstCategoriaResiduos",
						abstractCategoriaDao.listar("Categoria"));
				request.getRequestDispatcher("lstCategoriaResiduos.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("salvar")) {
				save(request);
				request.setAttribute("msg", "Operacao Realizada");
				request.setAttribute("objCategoriaResiduo", new Categoria());
				request.setAttribute("lstCategoriaResiduos",
						abstractCategoriaDao.listar("Categoria"));
				request.getRequestDispatcher("lstCategoriaResiduos.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("excluir")) {
				Long id = Long.parseLong(request.getParameter("id"));
				abstractCategoriaDao.remover(abstractCategoriaDao.obter(id,Categoria.class));
				request.setAttribute("lstCategoriaResiduos",
						abstractCategoriaDao.listar("Categoria"));
				request.getRequestDispatcher("lstCategoriaResiduos.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("editar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				request.setAttribute("objCategoriaResiduo",
						abstractCategoriaDao.obter(id,Categoria.class));
				request.getRequestDispatcher("frmCategoriaResiduo.jsp")
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
		Categoria categoria = (Categoria) PopulateObject.createObjectBusiness(
				new Categoria(), request);

		try {

			abstractCategoriaDao.salvar(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Categoria> lookupAbstractDaoLocal() {
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
