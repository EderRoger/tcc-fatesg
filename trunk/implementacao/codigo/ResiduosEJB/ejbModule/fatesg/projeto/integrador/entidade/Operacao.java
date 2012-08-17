/**
 * 
 */
package fatesg.projeto.integrador.entidade;

import java.util.Arrays;
import java.util.List;

public enum Operacao {
	
		
	VENDA(1,"VENDA"),COMPRA(2,"COMPRA");
	
	private int id;
	private String descricao;
	
	
	public int getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}


	private Operacao(int id,String descricao)
	{
	    this.id=id;
	    this.descricao=descricao;
	}
	
	public static Operacao getOperacaoPeloId(int id)
    {
        for (int i = 0; i < values().length; i++) {
            if(values()[i].id == id)               
                return values()[i];
        }
        return null;
    }
    
    public static List<Operacao> getValues()
    {
        return Arrays.asList(values());
    }

}
