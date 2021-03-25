package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				case 0:
					JOptionPane.showMessageDialog(null , "Programa finalizado pelo usuário!");
					break;
				}
				break;
			case OPTION_VEICULOS:
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
		
		Optional<Pessoa> pessoa = listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
		
		
		if(pessoa.isPresent()) {
			int indexPessoa = listaClientes.indexOf(pessoa.get());
			
			listaClientes.remove(indexPessoa);
			
			JOptionPane.showMessageDialog(null , "Cliente removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}	
	}	

}
