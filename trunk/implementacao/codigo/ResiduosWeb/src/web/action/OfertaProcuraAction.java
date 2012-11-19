/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import java.io.IOException;
import java.util.List;
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
import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.OfertaProcura;
import fatesg.projeto.integrador.entidade.Residuo;

/**
 * 
 * @author Professor
 */
public class OfertaProcuraAction implements BusinessLogic {

	AbstractDAO<OfertaProcura> abstractOfertaProcuraDao = lookupAbstractDaoLocal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		try {
			if (acao.equals("novo")) {
				request.setAttribute("objOfertaProcura", new OfertaProcura(0l));
				request.setAttribute("lstResiduo",
						abstractOfertaProcuraDao.listar("Residuo"));
				
				request.getRequestDispatcher("frmOfertaProcura.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("listar")) {
				List<OfertaProcura> lstOfertas = abstractOfertaProcuraDao.obterOfertaProcura(1);
				List<OfertaProcura> lstProcura = abstractOfertaProcuraDao.obterOfertaProcura(2);
				request.setAttribute("lstOferta", lstOfertas);
				request.setAttribute("qtdOfertas",lstOfertas.size());
				request.setAttribute("lstProcura", lstProcura);
				request.setAttribute("qtdProcuras", lstProcura.size());
				request.getRequestDispatcher("lstOfertaProcura.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("salvar")) {
				save(request);
				request.setAttribute("msg", "Operacao Realizada");
				request.setAttribute("objOfertaProcura", new OfertaProcura());
				List<OfertaProcura> lstOfertas = abstractOfertaProcuraDao.obterOfertaProcura(1);
				List<OfertaProcura> lstProcura = abstractOfertaProcuraDao.obterOfertaProcura(2);
				request.setAttribute("lstOferta", lstOfertas);
				request.setAttribute("qtdOfertas",lstOfertas.size());
				request.setAttribute("lstProcura", lstProcura);
				request.setAttribute("qtdProcuras", lstProcura.size());
				request.getRequestDispatcher("lstOfertaProcura.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("excluir")) {
				Long id = Long.parseLong(request.getParameter("id"));
				abstractOfertaProcuraDao.remover(abstractOfertaProcuraDao.obter(id,OfertaProcura.class));
				List<OfertaProcura> lstOfertas = abstractOfertaProcuraDao.obterOfertaProcura(1);
				List<OfertaProcura> lstProcura = abstractOfertaProcuraDao.obterOfertaProcura(2);
				request.setAttribute("lstOferta", lstOfertas);
				request.setAttribute("qtdOfertas",lstOfertas.size());
				request.setAttribute("lstProcura", lstProcura);
				request.setAttribute("qtdProcuras", lstProcura.size());
				request.getRequestDispatcher("lstOfertaProcura.jsp")
						.forward(request, response);
				return;
			}
			if (acao.equals("editar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				request.setAttribute("objOfertaProcura",
						abstractOfertaProcuraDao.obter(id,OfertaProcura.class));
				request.setAttribute("lstResiduo",
						abstractOfertaProcuraDao.listar("Residuo"));
				request.getRequestDispatcher("frmOfertaProcura.jsp")
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
		long idCategoria = Long.parseLong(request.getParameter("id_residuo"));
		Residuo residuo = lookupCategoriaLocal().obter(idCategoria,Residuo.class);
		
		long idIndustria = Long.parseLong(request.getParameter("id_industria"));
		Industria industria = lookupIndustriaLocal().obter(idIndustria,Industria.class);
		
		OfertaProcura ofertaProcura = (OfertaProcura) PopulateObject.createObjectBusiness(
				new OfertaProcura(), request);
		
		ofertaProcura.setResiduo(residuo);
		ofertaProcura.setIndustria(industria);
		
		try {

			abstractOfertaProcuraDao.salvar(ofertaProcura);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<OfertaProcura> lookupAbstractDaoLocal() {
		try {
			Properties p = new Properties();
			p.load(this.getClass().getResourceAsStream("jndi.properties"));
			Context c = new InitialContext(p);
			return (AbstractDAO<OfertaProcura>) c.lookup("dao/remote");
		} catch (Exception ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,
					"exception caught", ne);
			throw new RuntimeException(ne);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private AbstractDAO<Residuo> lookupCategoriaLocal() {
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
	private AbstractDAO<Industria> lookupIndustriaLocal() {
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

}
