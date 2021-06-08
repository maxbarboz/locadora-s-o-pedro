package interfaces;

import domain.Pessoa;
import domain.PessoaFisica;
import domain.PessoaJuridica;

import java.util.List;

public interface PessoaInterface {
	
	String verificarTipoPessoa();

	List cadastrarPessoaFisica(List listaClientes);

	List cadastrarPessoaJuridica(List listaClientes);
	
	void listarClientes(List<PessoaFisica> listaClientes, List<PessoaJuridica> listaJuridica);
	
	void removerClientePorCodigo(List<PessoaFisica> listaClientes, List<PessoaJuridica> listJuridica);
	
	/**
     * Método default que verifica o código de pessoa tanto para 
     * pessoa jurídica quanto pessoa física
     */
	default boolean verificaCodigoPessoa(List<Pessoa> listaClientes, String codigo) {
		return listaClientes.stream().anyMatch(entidade -> entidade.getCodigo().equals(codigo));
	}	
}
