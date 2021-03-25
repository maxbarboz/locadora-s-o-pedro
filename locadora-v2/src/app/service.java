package app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.util.StringUtils;

import classes.Locacao;
import classes.Pessoa;
import classes.Veiculo;
import util.Menu;
import util.Scroll;

public class service {

	public static void main(String[] args) {

		final int OPTION_PESSOAS = 1;
		final int OPTION_VEICULOS = 2;
		final int OPTION_LOCACOES = 3;

		/* Listas onde serão salvas os registros */
		List<Pessoa> listaPessoas = new ArrayList();
		List<Veiculo> listaVeiculos = new ArrayList();
		List<Locacao> listaLocacoes = new ArrayList();

		int opcaoMenu = 0;
		int opcaoMenuSecundario = 0;
		JOptionPane.showMessageDialog(null ,  Menu.getMessageIntro());
		
		opcaoMenu = Menu.menu();

		do {
			switch (opcaoMenu) {
			case OPTION_PESSOAS:
				opcaoMenuSecundario = Menu.getMenuPessoas();
				switch(opcaoMenuSecundario) {
				case 1:
					cadastrarCliente(listaPessoas);
					JOptionPane.showMessageDialog(null , "Cliente cadastrado com sucesso!");
					break;
				case 2:
					listarClientes(listaPessoas);
					break;
				case 3:
					removerClientePorCodigo(listaPessoas);
					break;
				case 4:
					opcaoMenu = Menu.menu();
					break;
				case 0:
					JOptionPane.showMessageDialog(null , "Programa finalizado pelo usuário!");
					break;
				}
				break;
			case OPTION_VEICULOS:
				opcaoMenuSecundario = Menu.getMenuVeiculos();
				switch(opcaoMenuSecundario) {
				case 1:
					cadastrarVeiculo(listaVeiculos);
					JOptionPane.showMessageDialog(null , "Veiculo cadastrado com sucesso!");
					break;
				case 2:
					listarVeiculos(listaVeiculos);
					break;
				case 3:
					removerVeiculoPorPlaca(listaVeiculos);
					break;
				case 4:
					opcaoMenu = Menu.menu();
					break;
				case 0:
					JOptionPane.showMessageDialog(null , "Programa finalizado pelo usuário!");
					break;
				}
				break;
			case OPTION_LOCACOES:
				opcaoMenuSecundario = Menu.getMenuLocacoes();
				switch(opcaoMenuSecundario) {
				case 1:
					cadastrarLocacao(listaLocacoes, listaPessoas, listaVeiculos);
					JOptionPane.showMessageDialog(null , "Locação cadastrada com sucesso!");
					break;
				case 2:
					listarLocacao(listaLocacoes);
					break;
				case 3:
					deletarLocacao(listaLocacoes);
					break;
				case 4:
					opcaoMenu = Menu.menu();
					break;
				case 0:
					JOptionPane.showMessageDialog(null , "Programa finalizado pelo usuário!");
					break;
				}
			case 0:
			}
		} while(opcaoMenu != 0 && opcaoMenuSecundario != 0);
		
	}

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
						.append("Data Locação: " + locacao.getDataLocacao().toString()).append("\n")
						.append("Quilometragem: " + locacao.getVeiculo().getQuilometragem()).append("\n\n");
			});
		}

		new Scroll(exibicao.toString(), "LISTAGEM DE LOCAÇÕES:");
	}

	public static void cadastrarLocacao(List<Locacao> locacoes, List<Pessoa> pessoas, List<Veiculo> veiculos){
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
			cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		Optional<Veiculo> optionalVeiculo = buscarVeiculoPorCodigo(placaVeiculo, veiculos);
		Veiculo veiculo = new Veiculo();

		if(optionalVeiculo.isPresent()){
			veiculo = optionalVeiculo.get();
		} else {
			cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		if(codigo.isEmpty() || codigoPessoa.isEmpty() || placaVeiculo.isEmpty()){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			cadastrarLocacao(locacoes, pessoas, veiculos);
		}

		locacoes.add(new Locacao(codigo, pessoa, veiculo, dataAtual, valor));
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
	
	public static void cadastrarCliente(List listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String nome = JOptionPane.showInputDialog(null, "Informe o nome:");
		String sexo =  JOptionPane.showInputDialog(null, "Informe o sexo (M - Masculino e F - Feminino):");
		String cpf = JOptionPane.showInputDialog(null, "Informe o CPF:");
		String localidade = JOptionPane.showInputDialog(null, "Informe a localidade: \n\nobs.: Localidade é opcional");
		
		if(verificaCodigoPessoa(listaClientes, codigo)) {
			JOptionPane.showMessageDialog(null , "Código já utilizado, favor informar outro!");
			cadastrarCliente(listaClientes);
		}
		
		if(!verificaSexoPessoa(sexo)) {
			JOptionPane.showMessageDialog(null , "Favor informar um valor válido (M - Masculino e F - Feminino):");
			cadastrarCliente(listaClientes);
		}
		
		if(!verificaCpf(cpf)) {
			JOptionPane.showMessageDialog(null , "Favor informar um CPF válido (COM 11 DIGITOS):");
			cadastrarCliente(listaClientes);
		}
		
		if(codigo.isEmpty() || nome.isEmpty() || sexo.isEmpty() || cpf.isEmpty()) {
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			cadastrarCliente(listaClientes);
		}
		
		listaClientes.add(localidade.isEmpty() ? new Pessoa(codigo, nome, sexo, cpf) : new Pessoa(codigo, nome, sexo, localidade, cpf));
	}
	
	public static boolean verificaCodigoPessoa(List<Pessoa> listaClientes, String codigo) {
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst().isPresent();	
	}
	
	public static boolean verificaSexoPessoa(String sexo) {
		return (sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F")) ? true : false;	
	}
	
	public static boolean verificaCpf(String cpf) {
		return cpf.length() == 11 ? true : false;	
	}
	
	public static void listarClientes(List<Pessoa> listaClientes) {
		StringBuilder exibicao = new StringBuilder();
		
		if(listaClientes.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem");
		} else {
			listaClientes.forEach(pessoa -> {
				exibicao.append("Código: " + pessoa.getCodigo()).append("\n")
						.append("Nome: " + pessoa.getNome()).append("\n")
						.append("Localidade: " + pessoa.getLocalidade()).append("\n")
						.append("Cpf: " + pessoa.getCpf()).append("\n")
						.append("Sexo: " + pessoa.getSexo()).append("\n\n");
			});
		}
		
		Scroll scroll = new Scroll(exibicao.toString(), "LISTAGEM DE CLIENTES:");
	}
	
	public static void removerClientePorCodigo(List<Pessoa> listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código do cliente a ser removido:");
		
		Optional<Pessoa> pessoa = buscarPessoaPorCodigo(codigo, listaClientes);

		if(pessoa.isPresent()) {
			int indexPessoa = listaClientes.indexOf(pessoa.get());
			
			listaClientes.remove(indexPessoa);
			
			JOptionPane.showMessageDialog(null , "Cliente removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}	
	}

	public static void cadastrarVeiculo(List listaVeiculos) {
		String placa = JOptionPane.showInputDialog(null, "Informe o placa do veículo:");
		String marca = JOptionPane.showInputDialog(null, "Informe a marca:");
		String nomeVeiculo =  JOptionPane.showInputDialog(null, "Informe o nome do veículo:");

		if(!verificaPlacaVeiculo(placa)) {
			JOptionPane.showMessageDialog(null , "Favor informar uma placa válida: 7 DIGITOS!");
			cadastrarVeiculo(listaVeiculos);
		}
		
		if(placa.isEmpty() || marca.isEmpty() || nomeVeiculo.isEmpty() || quilometragem.isEmpty()){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			cadastrarVeiculo(listaVeiculos);
		}

		listaVeiculos.add(new Veiculo(placa, marca, nomeVeiculo, Integer.parseInt(quilometragem)));
	}
	
	public static boolean verificaPlacaVeiculo(String placa) {	
		return placa.length() == 7 ? true : false;
	}

	public static void listarVeiculos(List<Veiculo> listaVeiculos) {
		StringBuilder exibicao = new StringBuilder();

		exibicao.append("LISTAGEM DE VEICULOS:\n\n");

		if(listaVeiculos.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem");
		} else {
			listaVeiculos.forEach(veiculo -> {
				exibicao.append("Placa: " + veiculo.getPlaca()).append("\n")
						.append("Marca: " + veiculo.getMarca()).append("\n")
						.append("Nome do veiculo: " + veiculo.getNomeVeiculo()).append("\n")
						.append("Quilometragem: " + veiculo.getQuilometragem()).append("\n");
			});
		}

		JOptionPane.showMessageDialog(null , exibicao);
	}

	public static void removerVeiculoPorPlaca(List<Veiculo> listaVeiculos) {
		String placa = JOptionPane.showInputDialog(null, "Informe a placa do veiculo a ser removido:");

		Optional<Veiculo> veiculo = listaVeiculos.stream().filter(entidade -> entidade.getPlaca().equals(placa)).findFirst();


		if(veiculo.isPresent()) {
			int indexVeiculo = listaVeiculos.indexOf(veiculo.get());

			listaVeiculos.remove(indexVeiculo);

			JOptionPane.showMessageDialog(null , "Veiculo removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}
	}

}


