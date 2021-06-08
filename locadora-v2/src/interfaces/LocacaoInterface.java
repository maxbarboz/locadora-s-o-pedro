package interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import domain.Locacao;
import domain.PessoaFisica;
import domain.PessoaJurisdica;
import domain.Veiculo;
import util.Scroll;

public interface LocacaoInterface {
	
	void listarLocacaoComFiltro(List<Locacao> locacoes);
	
	void listarLocacaoSemFiltro(List<Locacao> locacoes);
	
	List cadastrarLocacao(List<Locacao> locacoes, List<PessoaFisica> pessoas, List<Veiculo> veiculos);

	List cadastrarLocacaoPessoaJurisdica(List<Locacao> locacoes, List<PessoaJurisdica> pessoas, List<Veiculo> veiculos);
	
	void deletarLocacao(List<Locacao> locacoes);
	
}
