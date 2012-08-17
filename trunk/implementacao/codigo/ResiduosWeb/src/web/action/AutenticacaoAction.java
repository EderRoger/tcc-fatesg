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
import web.util.CriptografiaMD5;
import fatesg.projeto.integrador.dao.AbstractDAO;
import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.OfertaProcura;
import fatesg.projeto.integrador.entidade.Residuo;
import fatesg.projeto.integrador.entidade.Usuario;

/**
 *
 * @author Professor
 */
public class AutenticacaoAction implements BusinessLogic {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("acao");
        AbstractDAO<OfertaProcura> abstractOfertaProcuraDao = lookupOfertaProcuraLocal();
        AbstractDAO<Usuario> usuarioAbstract = lookupAbstractDaoLocal();

        if (action.equals("logar")) {
            String login = request.getParameter("login").toLowerCase();
            String senha = request.getParameter("senha");
            senha = CriptografiaMD5.encrypt(senha);
            Usuario user = null;
            try {
            	user = usuarioAbstract.FindByUser(login, senha);
			} catch (Exception e) {
				request.setAttribute("msg", "Login ou Senha estão incorretos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
			}
            
            if (user != null) {
                if (user.getStatus()) {
                    request.getSession().setAttribute("objUsuarioSS", user);
                    List<OfertaProcura> lstOfertas = abstractOfertaProcuraDao.obterOfertaProcura(1);
    				List<OfertaProcura> lstProcura = abstractOfertaProcuraDao.obterOfertaProcura(2);
    				request.setAttribute("lstOferta", lstOfertas);
    				request.setAttribute("qtdOfertas",lstOfertas.size());
    				request.setAttribute("lstProcura", lstProcura);
    				request.setAttribute("qtdProcuras", lstProcura.size());
    				
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "O Usuário esta INATIVO, favor entrar em contato com o departamento financeiro.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
                return;
            } else {
                request.setAttribute("msg", "Erro de login ou senha.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        }
        if (action.equals("logout")) {
            request.getSession().removeAttribute("objUsuarioSS");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @SuppressWarnings({  "unchecked" })
	private AbstractDAO<Usuario> lookupAbstractDaoLocal() {
        try {
        	Properties p =new Properties();
    		p.load(this.getClass().getResourceAsStream("jndi.properties"));
            Context c = new InitialContext(p);
            return (AbstractDAO<Usuario>) c.lookup("dao/remote");
        } catch (Exception ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    @SuppressWarnings({ "unchecked" })
	private AbstractDAO<OfertaProcura> lookupOfertaProcuraLocal() {
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
   
    
}
