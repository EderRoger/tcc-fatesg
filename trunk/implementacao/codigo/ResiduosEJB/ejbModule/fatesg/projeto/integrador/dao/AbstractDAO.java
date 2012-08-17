package fatesg.projeto.integrador.dao;

import java.util.List;

import javax.ejb.Remote;

import fatesg.projeto.integrador.entidade.Industria;
import fatesg.projeto.integrador.entidade.OfertaProcura;
import fatesg.projeto.integrador.entidade.Usuario;

@SuppressWarnings("hiding")
@Remote
public interface AbstractDAO<T> {

	public <T> void salvar(T element);

	public <T> void editar(T element);

	public <T> void remover(T element);

	public T obter(Long id,Class clazz);

	public List<T> listar(String nome);

	public List<T> listar(String nome, String campoOrder);

	public List<T> listar(String nome, String campoOrder, String campoBusca,
			String tipo, String where);
	
	public Usuario FindByUser(String login, String senha);
	public List<OfertaProcura> obterOfertaProcura(int operacao);
	public List<Usuario> obterUsuarios(Industria industria);
}
