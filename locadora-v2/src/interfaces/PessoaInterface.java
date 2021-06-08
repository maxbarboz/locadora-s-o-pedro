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
	
	default boolean verificaCodigoPessoa(List<Pessoa> listaClientes, String codigo) {
		return listaClientes.stream().anyMatch(entidade -> entidade.getCodigo().equals(codigo));
	}
	
	default boolean verificaSexoPessoa(String sexo) {
		return sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F");
	}
	
}
