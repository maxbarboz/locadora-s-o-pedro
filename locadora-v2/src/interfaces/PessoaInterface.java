package interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import domain.Locacao;
import domain.Pessoa;
import domain.PessoaFisica;
import domain.PessoaJurisdica;
import domain.Veiculo;
import util.Scroll;

public interface PessoaInterface {
	
	String verificarTipoPessoa();

	List cadastrarPessoaFisica(List listaClientes);

	List cadastrarPessoaJurisdica(List listaClientes);
	
	void listarClientes(List<PessoaFisica> listaClientes, List<PessoaJurisdica> listaJurisdica);
	
	void removerClientePorCodigo(List<PessoaFisica> listaClientes);
	
	default boolean verificaCodigoPessoa(List<Pessoa> listaClientes, String codigo) {
		return listaClientes.stream().anyMatch(entidade -> entidade.getCodigo().equals(codigo));
	}
	
	default boolean verificaSexoPessoa(String sexo) {
		return sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F");
	}
	
}
