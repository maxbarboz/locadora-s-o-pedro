package services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import domain.Locacao;
import domain.Pessoa;
import domain.Veiculo;
import util.Scroll;

public class LocacaoService {

	public static void listarLocacao(List<Locacao> locacoes){
		String filtroPessoa = JOptionPane.showInputDialog(null, "Informe o filtro de pessoa:");
		String filtroVeiculo = JOptionPane.showInputDialog(null, "Informe o filtro do veiculo:");

		List<Locacao> locacoesFlitradas;

		if(filtroVeiculo.isEmpty() && filtroPessoa.isEmpty()){
			locacoesFlitradas = locacoes;
		} else {
			locacoesFlitradas = locacoes.stream().filter(item -> item.getPessoa().getNome().equals(filtroPessoa) || item.getVeiculo().equals(filtroVeiculo))
					.collect(Collectors.toList());
		}

		StringBuilder exibicao = new StringBuilder();

		if(locacoesFlitradas.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem");
		} else {
			locacoesFlitradas.forEach(locacao -> {
				exibicao.append("Código: " + locacao.getCodigo()).append("\n")
						.append("Nome: " + locacao.getPessoa().getNome()).append("\n")
						.append("Nome do Veículo: " + locacao.getVeiculo().getNomeVeiculo()).append("\n")
						.append("Placa: " + locacao.getVeiculo().getPlaca()).append("\n")
						.append("Data Locação: " + locacao.getDataLocacao().toString()).append("\n");
			});
		}

		new Scroll(exibicao.toString(), "LISTAGEM DE LOCAÇÕES:");
	}
	
	public static List cadastrarLocacao(List<Locacao> locacoes, List<Pessoa> pessoas, List<Veiculo> veiculos){
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String codigoPessoa = JOptionPane.showInputDialog(null, "Informe o codigo da Pessoa:");
		String placaVeiculo =  JOptionPane.showInputDialog(null, "Informe a placa do Veículo:");
		Long valor = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o valor da Locação:"));

		LocalDate dataAtual = LocalDate.now();

		Optional<Pessoa> pessoaOptional = buscarPessoaPorCodigo(codigoPessoa, pessoas);
		Pessoa pessoa = new Pessoa();

		if(pessoaOptional.isPresent()){
			pessoa = pessoaOptional.get();
		} else {
			return cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		Optional<Veiculo> optionalVeiculo = buscarVeiculoPorCodigo(placaVeiculo, veiculos);
		Veiculo veiculo = new Veiculo();

		if(optionalVeiculo.isPresent()){
			veiculo = optionalVeiculo.get();
		} else {
			return cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		if(codigo.isEmpty() || codigoPessoa.isEmpty() || placaVeiculo.isEmpty()){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		locacoes.add(new Locacao(codigo, pessoa, veiculo, dataAtual, valor));
		
		return locacoes;
	}

	public static Optional<Veiculo> buscarVeiculoPorCodigo(String placa, List<Veiculo> listarVeiculo){
		return listarVeiculo.stream().filter(entidade -> entidade.getPlaca().equals(placa)).findFirst();
	}

	public static Optional<Pessoa> buscarPessoaPorCodigo(String codigo, List<Pessoa> listaClientes){
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
	}
	
	public static void deletarLocacao(List<Locacao> locacoes){
		String codigo = JOptionPane.showInputDialog(null, "Informe o código da locação a ser removido:");

		locacoes.removeIf(item -> item.getCodigo().equals(codigo));
	}
}
