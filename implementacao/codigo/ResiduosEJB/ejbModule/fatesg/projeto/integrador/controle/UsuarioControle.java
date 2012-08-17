package fatesg.projeto.integrador.controle;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fatesg.projeto.integrador.dao.UsuarioDAO;
import fatesg.projeto.integrador.entidade.Usuario;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioControle implements Serializable {

	private static final long serialVersionUID = -6474232048022695451L;

	@EJB
	private UsuarioDAO usuarioDAO;

	public void gravarCliente(Usuario usuario) {

		usuarioDAO.salvar(usuario);
		
	}

}
