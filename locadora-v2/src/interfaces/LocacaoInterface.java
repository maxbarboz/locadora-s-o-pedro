package interfaces;

import java.util.List;

import domain.Locacao;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Veiculo;

public interface LocacaoInterface {
	
	void listarLocacaoComFiltro(List<Locacao> locacoes);
	
	void listarLocacaoSemFiltro(List<Locacao> locacoes);
	
	List cadastrarLocacao(List<Locacao> locacoes, List<PessoaFisica> pessoas, List<Veiculo> veiculos);

	List cadastrarLocacaoPessoaJuridica(List<Locacao> locacoes, List<PessoaJuridica> pessoas, List<Veiculo> veiculos);
	
	void deletarLocacao(List<Locacao> locacoes);
	
}
