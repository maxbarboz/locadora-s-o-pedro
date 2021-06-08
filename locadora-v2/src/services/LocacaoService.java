package services;

import domain.Locacao;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Veiculo;
import interfaces.LocacaoInterface;
import util.Scroll;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocacaoService implements LocacaoInterface {

	@Override
	public void listarLocacaoComFiltro(List<Locacao> locacoes){
		String filtroPessoa = JOptionPane.showInputDialog(null, "Informe o filtro de pessoa:");
		String filtroVeiculo = JOptionPane.showInputDialog(null, "Informe o filtro de veículo:");

		List<Locacao> locacoesFlitradas;

		if(filtroVeiculo.isEmpty() && filtroPessoa.isEmpty()){
			locacoesFlitradas = locacoes;
		} else {
			locacoesFlitradas = locacoes.stream().filter(item ->
					item.getPessoaFisica() != null ?
							item.getPessoaFisica().getNome().equalsIgnoreCase(filtroPessoa) :
							item.getPessoajuridica().getRazaoSocial().equalsIgnoreCase(filtroPessoa) ||
					item.getVeiculo().getPlaca().equalsIgnoreCase(filtroVeiculo)).collect(Collectors.toList());
		}

		StringBuilder exibicao = new StringBuilder();

		if(locacoesFlitradas.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem");
		} else {
			locacoesFlitradas.forEach(locacao -> {
				exibicao.append("Código: " + locacao.getCodigo()).append("\n")
						.append("Nome: " + locacao.getPessoajuridica() == null ? locacao.getPessoaFisica().getNome() : locacao.getPessoajuridica().getRazaoSocial()).append("\n")
						.append("Nome do Veículo: " + locacao.getVeiculo().getNomeVeiculo()).append("\n")
						.append("Placa: " + locacao.getVeiculo().getPlaca()).append("\n")
						.append("Data Locação: " + locacao.getDataLocacao().toString()).append("\n\n");
			});
		}

		new Scroll(exibicao.toString(), "LISTAGEM DE LOCAÇÕES DETALHADA:");
	}
	
	@Override
	public void listarLocacaoSemFiltro(List<Locacao> locacoes){
		
		StringBuilder exibicao = new StringBuilder();

		if(locacoes.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem");
		} else {
			locacoes.forEach(locacao -> {
				exibicao.append("Código: " + locacao.getCodigo()).append("\n")
						.append("Nome: " + locacao.getPessoaFisica() != null ? locacao.getPessoaFisica().getNome() : locacao.getPessoajuridica().getRazaoSocial()).append("\n")
						.append("Nome do Veículo: " + locacao.getVeiculo().getNomeVeiculo()).append("\n")
						.append("Placa: " + locacao.getVeiculo().getPlaca()).append("\n")
						.append("Data Locação: " + locacao.getDataLocacao().toString()).append("\n\n");
			});
		}

		new Scroll(exibicao.toString(), "LISTAGEM DE LOCAÇÕES:");
	}
	
	@Override
	public List cadastrarLocacao(List<Locacao> locacoes, List<PessoaFisica> pessoas, List<Veiculo> veiculos){
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String codigoPessoa = JOptionPane.showInputDialog(null, "Informe o código da Pessoa:");
		String placaVeiculo =  JOptionPane.showInputDialog(null, "Informe a placa do Veículo:");
		Long valor = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o valor da Locação:"));

		LocalDate dataAtual = LocalDate.now();

		if(verificaCodigoLocacao(locacoes, codigo)) {
			JOptionPane.showMessageDialog(null , "Código já utilizado, favor informar outro!");
			return cadastrarLocacao(locacoes, pessoas, veiculos);
		}
		
		Optional<PessoaFisica> pessoaOptional = buscarPessoaPorCodigo(codigoPessoa, pessoas);
		
		if(pessoaOptional.isEmpty()){
			JOptionPane.showMessageDialog(null , "Pessoa não encontrada nos registros, favor consultar código na listagem!");
			return locacoes;
		}

		Optional<Veiculo> optionalVeiculo = buscarVeiculoPorCodigo(placaVeiculo, veiculos);

		if(!optionalVeiculo.isPresent()){
			JOptionPane.showMessageDialog(null , "Placa do veículo não encontrada nos registros, favor consultar código na listagem!");
			return locacoes;
		}

		if(codigo.isEmpty() || codigoPessoa.isEmpty() || placaVeiculo.isEmpty()){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		locacoes.add(new Locacao(codigo, pessoaOptional.get(), optionalVeiculo.get(), dataAtual, valor));
		JOptionPane.showMessageDialog(null , "Locação cadastrada com sucesso!");
		
		return locacoes;
	}

	@Override
	public  List cadastrarLocacaoPessoaJuridica(List<Locacao> locacoes, List<PessoaJuridica> pessoas, List<Veiculo> veiculos){
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String codigoPessoa = JOptionPane.showInputDialog(null, "Informe o codigo da Pessoa:");
		String placaVeiculo =  JOptionPane.showInputDialog(null, "Informe a placa do Veículo:");
		Long valor = Long.parseLong(JOptionPane.showInputDialog(null, "Informe o valor da Locação:"));

		LocalDate dataAtual = LocalDate.now();

		if(verificaCodigoLocacao(locacoes, codigo)) {
			JOptionPane.showMessageDialog(null , "Código já utilizado, favor informar outro!");
			return cadastrarLocacaoPessoaJuridica(locacoes, pessoas, veiculos);
		}

		Optional<PessoaJuridica> pessoaOptional = buscarPessoajuridicaPorCodigo(codigoPessoa, pessoas);

		if(pessoaOptional.isEmpty()){
			JOptionPane.showMessageDialog(null , "Pessoa não encontrada nos registros, favor consultar código na listagem!");
			return locacoes;
		}

		Optional<Veiculo> optionalVeiculo = buscarVeiculoPorCodigo(placaVeiculo, veiculos);

		if(!optionalVeiculo.isPresent()){
			JOptionPane.showMessageDialog(null , "Placa do veículo não encontrada nos registros, favor consultar código na listagem!");
			return locacoes;
		}

		if(codigo.isEmpty() || codigoPessoa.isEmpty() || placaVeiculo.isEmpty()){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarLocacaoPessoaJuridica(locacoes, pessoas, veiculos);
		}

		locacoes.add(new Locacao(codigo, pessoaOptional.get(), optionalVeiculo.get(), dataAtual, valor));
		JOptionPane.showMessageDialog(null , "Locação cadastrada com sucesso!");

		return locacoes;
	}

	public static Optional<Veiculo> buscarVeiculoPorCodigo(String placa, List<Veiculo> listarVeiculo){
		return listarVeiculo.stream().filter(entidade -> entidade.getPlaca().equals(placa)).findFirst();
	}

	public static Optional<PessoaFisica> buscarPessoaPorCodigo(String codigo, List<PessoaFisica> listaClientes){
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
	}

	public static Optional<PessoaJuridica> buscarPessoajuridicaPorCodigo(String codigo, List<PessoaJuridica> listaClientes){
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
	}
	
	@Override
	public void deletarLocacao(List<Locacao> locacoes){
		String codigo = JOptionPane.showInputDialog(null, "Informe o código da locação a ser removido:");
		if(verificaCodigoLocacao(locacoes, codigo)) {
			locacoes.removeIf(item -> item.getCodigo().equals(codigo));
			JOptionPane.showMessageDialog(null , "Registro Locação removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}

	}
	
	public static boolean verificaCodigoLocacao(List<Locacao> listaLocacoes, String codigo) {
		return listaLocacoes.stream().anyMatch(entidade -> entidade.getCodigo().equals(codigo));
	}
}
