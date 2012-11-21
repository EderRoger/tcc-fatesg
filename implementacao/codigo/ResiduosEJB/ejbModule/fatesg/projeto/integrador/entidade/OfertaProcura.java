/**
 * 
 */
package fatesg.projeto.integrador.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ofertaProcura")
public class OfertaProcura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8427870558840563698L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "industria_id", nullable = false, updatable = false)
	private Industria industria;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "residuo_id", nullable = false, updatable = false)
	private Residuo residuo;
	@Column(name = "operacao")
	private int operacao;
	@Column(name = "quantidade")
	private int quantidade;
	@Column(name = "preco")
	private double preco;
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	

	public OfertaProcura(Long id) {
		super();
		this.id = id;
	}
	public OfertaProcura() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Industria getIndustria() {
		return industria;
	}

	public void setIndustria(Industria industria) {
		this.industria = industria;
	}

	public Residuo getResiduo() {
		return residuo;
	}

	public void setResiduo(Residuo residuo) {
		this.residuo = residuo;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
