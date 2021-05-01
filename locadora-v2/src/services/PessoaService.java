package services;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import domain.Pessoa;
import util.Scroll;

public class PessoaService {

	public static List cadastrarCliente(List listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String nome = JOptionPane.showInputDialog(null, "Informe o nome:");
		String sexo =  JOptionPane.showInputDialog(null, "Informe o sexo (M - Masculino e F - Feminino):");
		String cpf = JOptionPane.showInputDialog(null, "Informe o CPF:");
		String localidade = JOptionPane.showInputDialog(null, "Informe a localidade: \n\nobs.: Localidade é opcional");
		
		if(verificaCodigoPessoa(listaClientes, codigo)) {
			JOptionPane.showMessageDialog(null , "Código já utilizado, favor informar outro!");
			return cadastrarCliente(listaClientes);
		}
		
		if(!verificaSexoPessoa(sexo)) {
			JOptionPane.showMessageDialog(null , "Favor informar um valor válido (M - Masculino e F - Feminino):");
			return cadastrarCliente(listaClientes);
		}
		
		if(!verificaCpf(cpf)) {
			JOptionPane.showMessageDialog(null , "Favor informar um CPF válido (COM 11 DIGITOS):");
			return cadastrarCliente(listaClientes);
		}
		
		if(codigo.isEmpty() || nome.isEmpty() || sexo.isEmpty() || cpf.isEmpty()) {
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarCliente(listaClientes);
		}
		
		listaClientes.add(localidade.isEmpty() ? new Pessoa(codigo, nome, sexo, cpf) : new Pessoa(codigo, nome, sexo, localidade, cpf));
		
		return listaClientes;
	}
	
	public static boolean verificaCodigoPessoa(List<Pessoa> listaClientes, String codigo) {
		return listaClientes.stream().anyMatch(entidade -> entidade.getCodigo().equals(codigo));
	}
	
	public static boolean verificaSexoPessoa(String sexo) {
		return sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F");
	}
	
	public static boolean verificaCpf(String cpf) {
		return cpf.length() == 11;
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
	
	public static Optional<Pessoa> buscarPessoaPorCodigo(String codigo, List<Pessoa> listaClientes){
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
	}
}
