package interfaces;

import domain.Pessoa;
import domain.PessoaFisica;
import domain.PessoaJurisdica;

import java.util.List;

public interface PessoaInterface {
	
	String verificarTipoPessoa();

	List cadastrarPessoaFisica(List listaClientes);

	List cadastrarPessoaJurisdica(List listaClientes);
	
	void listarClientes(List<PessoaFisica> listaClientes, List<PessoaJurisdica> listaJurisdica);
	
	void removerClientePorCodigo(List<PessoaFisica> listaClientes, List<PessoaJurisdica> listJurisdica);
	
	/**
     * Método default que verifica o código de pessoa tanto para 
     * pessoa jurídica quanto pessoa física
     */
	default boolean verificaCodigoPessoa(List<Pessoa> listaClientes, String codigo) {
		return listaClientes.stream().anyMatch(entidade -> entidade.getCodigo().equals(codigo));
	}	
}
