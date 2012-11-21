package fatesg.projeto.integrador.dao;

import java.util.List;

import javax.persistence.Query;

import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.Usuario;


public class UsuarioDAO extends Dao<Usuario, Long> {

	
    public UsuarioDAO(Class<?> classe) {
    	super(classe);
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> obterUsuario(Industria industria) {
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.industria = :industria");
		q.setParameter("Industria", industria);
		return q.getResultList();
	}
    
}
