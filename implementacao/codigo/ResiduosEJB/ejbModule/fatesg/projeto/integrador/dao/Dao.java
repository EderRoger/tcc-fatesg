package fatesg.projeto.integrador.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.OfertaProcura;
import fatesg.projeto.integrador.entidade.Usuario;

@Stateless(name = "dao")
public class Dao<T, PK> implements AbstractDAO<T> {

	@PersistenceContext(unitName = "ResiduosEJBPU")
	protected EntityManager em;

	private Class<?> classe;

	public Dao() {
	}

	public Dao(Class<?> classe) {
		this.classe = classe;
	}

	@SuppressWarnings("hiding")
	public <T> void salvar(T element) {
		try {
			em.merge(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("hiding")
	public <T> void editar(T element) {
		try {
			element = em.merge(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("hiding")
	public <T> void remover(T element) {
		try {
			element = em.merge(element);
			em.remove(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T obter(Long id,Class clazz) {
		T instance = null;
		try {
			instance = (T) em.find(clazz, id);
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> listar(String nome) {
		try {
			Query sql = em.createQuery("SELECT p FROM " + nome + " p ");
			return sql.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(String nome, String campoOrder) {
		try {
			Query sql = em.createQuery("SELECT p FROM " + nome + " p order by p." + campoOrder + " desc ");
			return sql.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(String nome, String campoOrder, String campoBusca, String tipo, String where) {
		try {
			Query sql = em.createQuery("SELECT p FROM " + nome + " p WHERE p." + campoBusca + " " + where + " '" + tipo + "' order by p." + campoOrder + " desc ");
			return sql.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario FindByUser(String login, String senha) {
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
		q.setParameter("senha", senha);
		q.setParameter("login", login);
		return (Usuario) q.getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<OfertaProcura> obterOfertaProcura(int operacao) {
		Query q = em.createQuery("SELECT u FROM OfertaProcura u WHERE u.operacao = :operacao");
		q.setParameter("operacao", operacao);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> obterUsuarios(Industria industria) {
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.industria.id = :industria");
			q.setParameter("industria", industria.getId());
			return q.getResultList();
		} catch (Exception e) {
			 return null;
		}
		
	}
	
	
}
