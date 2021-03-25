package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.util.StringUtils;

import classes.Locacao;
import classes.Pessoa;
import classes.Veiculo;
import util.Menu;

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
				case 0:
					JOptionPane.showMessageDialog(null , "Programa finalizado pelo usuário!");
					break;
				}
				break;
			case OPTION_LOCACOES:
				opcaoMenuSecundario = Menu.getMenuVeiculos();
				switch(opcaoMenuSecundario) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 0:
					JOptionPane.showMessageDialog(null , "Programa finalizado pelo usuário!");
					break;
				}
			case 0:
			}
		} while(opcaoMenu != 0 && opcaoMenuSecundario != 0);
		
	}
	
	public static void cadastrarCliente(List listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String nome = JOptionPane.showInputDialog(null, "Informe o nome:");
		String sexo =  JOptionPane.showInputDialog(null, "Informe o sexo (M - Masculino e F - Feminino):");
		String cpf = JOptionPane.showInputDialog(null, "Informe o CPF:");
		
		String localidade = JOptionPane.showInputDialog(null, "Informe a localidade: \n\nobs.: Localidade é opcional");
		
		if(codigo.isEmpty() || nome.isEmpty() || sexo.isEmpty() || cpf.isEmpty()) {
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			cadastrarCliente(listaClientes);
		}
		
		listaClientes.add(localidade.isEmpty() ? new Pessoa(codigo, nome, sexo, cpf) : new Pessoa(codigo, nome, sexo, localidade, cpf));
	}
	
	public static void listarClientes(List<Pessoa> listaClientes) {
		StringBuilder exibicao = new StringBuilder();
		
		exibicao.append("LISTAGEM DE CLIENTES:\n\n");
		
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
			
		JOptionPane.showMessageDialog(null , exibicao);
	}
	
	public static void removerClientePorCodigo(List<Pessoa> listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código do cliente a ser removido:");
		
		Optional<Pessoa> pessoa = listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
		
		
		if(pessoa.isPresent()) {
			int indexPessoa = listaClientes.indexOf(pessoa.get());
			
			listaClientes.remove(indexPessoa);
			
			JOptionPane.showMessageDialog(null , "Cliente removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}	
	}

	public static void cadastrarVeiculo(List listaVeiculos) {
		String placa = JOptionPane.showInputDialog(null, "Informe o placa do veiculo:");
		String marca = JOptionPane.showInputDialog(null, "Informe a marca:");
		String nomeVeiculo =  JOptionPane.showInputDialog(null, "Informe o nome do veiculo");
		Long quilometragem = Long.parseLong(JOptionPane.showInputDialog(null, "Informe a quilometragem"));

		if(placa.isEmpty() || marca.isEmpty() || nomeVeiculo.isEmpty() || Objects.isNull(quilometragem)){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			cadastrarVeiculo(listaVeiculos);
		}

		listaVeiculos.add( new Veiculo(placa, marca, nomeVeiculo, quilometragem) );

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


