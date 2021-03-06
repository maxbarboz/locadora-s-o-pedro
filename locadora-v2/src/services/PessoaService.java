package services;

import domain.PessoaFisica;
import domain.PessoaJuridica;
import interfaces.PessoaInterface;
import util.Scroll;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class PessoaService implements PessoaInterface {

	@Override
	public String verificarTipoPessoa(){
		String tipoPessoa = JOptionPane.showInputDialog(null,
				"Informe se voçê a uma pessoa fisica (1) ou pessoa juridica (2)");

		if(!tipoPessoa.equals("1") && !tipoPessoa.equals("2")){
			tipoPessoa = verificarTipoPessoa();
		}

		return tipoPessoa;
	}

	@Override
	public List cadastrarPessoaFisica(List listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String nome = JOptionPane.showInputDialog(null, "Informe o nome:");
		String sexo =  JOptionPane.showInputDialog(null, "Informe o sexo (M - Masculino e F - Feminino):");
		String cpf = JOptionPane.showInputDialog(null, "Informe o CPF:");
		String localidade = JOptionPane.showInputDialog(null, "Informe a localidade: \n\nobs.: Localidade é opcional");
		
		if(verificaCodigoPessoa(listaClientes, codigo)) {
			JOptionPane.showMessageDialog(null , "Código já utilizado, favor informar outro!");
			return cadastrarPessoaFisica(listaClientes);
		}
		
		if(!verificaSexoPessoa(sexo)) {
			JOptionPane.showMessageDialog(null , "Favor informar um valor válido (M - Masculino e F - Feminino):");
			return cadastrarPessoaFisica(listaClientes);
		}
		
		if(!verificaCpf(cpf)) {
			JOptionPane.showMessageDialog(null , "Favor informar um CPF válido (COM 11 DIGITOS):");
			return cadastrarPessoaFisica(listaClientes);
		}
		
		if(codigo.isEmpty() || nome.isEmpty() || sexo.isEmpty() || cpf.isEmpty()) {
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarPessoaFisica(listaClientes);
		}
		
		listaClientes.add(localidade.isEmpty() ? new PessoaFisica(codigo, cpf, nome, sexo) : new PessoaFisica(codigo, localidade, cpf, nome, sexo));
		
		return listaClientes;
	}

	@Override
	public List cadastrarPessoaJuridica(List listaClientes) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código de registro:");
		String razaoSocial = JOptionPane.showInputDialog(null, "Informe o nome:");
		String cnpj = JOptionPane.showInputDialog(null, "Informe o CNPJ:");
		String localidade = JOptionPane.showInputDialog(null, "Informe a localidade: \n\nobs.: Localidade é opcional");

		if(verificaCodigoPessoa(listaClientes, codigo)) {
			JOptionPane.showMessageDialog(null , "Código já utilizado, favor informar outro!");
			return cadastrarPessoaJuridica(listaClientes);
		}

		if(!verificacnpj(cnpj)) {
			JOptionPane.showMessageDialog(null , "Favor informar um CNPJ válido (COM 14 DIGITOS):");
			return cadastrarPessoaJuridica(listaClientes);
		}

		if(codigo.isEmpty() || razaoSocial.isEmpty() || cnpj.isEmpty()) {
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarPessoaJuridica(listaClientes);
		}

		listaClientes.add(localidade.isEmpty() ? new PessoaJuridica(codigo, cnpj, razaoSocial) : new PessoaJuridica(codigo, localidade, cnpj, razaoSocial));

		return listaClientes;
	}
	
	public static boolean verificaCpf(String cpf) {
		return cpf.length() == 11;
	}

	public static boolean verificacnpj(String cpf) {
		return cpf.length() == 14;
	}
	
	@Override
	public void listarClientes(List<PessoaFisica> listaClientes, List<PessoaJuridica> listajuridica) {
		StringBuilder exibicao = new StringBuilder();

		String tipoListar = JOptionPane.showInputDialog(null,
				"Deseja listar pessoa fisica(1). pessoa juridica(2), ou todos(0)");

		if("1".equals(tipoListar)){
			if(listaClientes.isEmpty()) {
				exibicao.append("\nNão existe registro para a listagem de pessoa fisica");
			}
			listarPessoaFisica(exibicao, listaClientes);
		}
		if("2".equals(tipoListar)){
			if(listajuridica.isEmpty()) {
				exibicao.append("\nNão existe registro para a listagem de pessoa juridica");
			}
			listarPessoajuridica(exibicao, listajuridica);
		}
		if("0".equals(tipoListar)){
			listarPessoaFisica(exibicao, listaClientes);
			listarPessoajuridica(exibicao, listajuridica);
		}

		Scroll scroll = new Scroll(exibicao.toString(), "LISTAGEM DE CLIENTES:");
		exibicao = new StringBuilder();
	}

	public static String listarPessoaFisica(StringBuilder exibicao, List<PessoaFisica> listaClientes){
		if(listaClientes.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem de pessoa fisica");
		} else {
			listaClientes.forEach(pessoa -> {
				exibicao.append("Código: " + pessoa.getCodigo()).append("\n")
						.append("Nome: " + pessoa.getNome()).append("\n")
						.append("Localidade: " + pessoa.getLocalidade()).append("\n")
						.append("Cpf: " + pessoa.getCpf()).append("\n")
						.append("Sexo: " + pessoa.getSexo()).append("\n\n");
			});
		}

		return exibicao.toString();
	}

	public static String listarPessoajuridica(StringBuilder exibicao, List<PessoaJuridica> listajuridica){
		if(listajuridica.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem de pessoa juridica");
		} else {
			listajuridica.forEach(pessoa -> {
				exibicao.append("Código: " + pessoa.getCodigo()).append("\n")
						.append("Nome: " + pessoa.getRazaoSocial()).append("\n")
						.append("Localidade: " + pessoa.getLocalidade()).append("\n")
						.append("Cnpj: " + pessoa.getCnpj()).append("\n\n");
			});
		}

		return exibicao.toString();
	}
	
	@Override
	public void removerClientePorCodigo(List<PessoaFisica> listaClientes, List<PessoaJuridica> listajuridica) {
		String codigo = JOptionPane.showInputDialog(null, "Informe o código do cliente a ser removido:");
		
		Optional<PessoaFisica> pessoa = buscarPessoaFisicaPorCodigo(codigo, listaClientes);
		Optional<PessoaJuridica> pessoaJuridica = buscarPessoaJuridicaPorCodigo(codigo, listajuridica);

		if(pessoa.isPresent()) {
			int indexPessoa = listaClientes.indexOf(pessoa.get());
			listaClientes.remove(indexPessoa);
			
			JOptionPane.showMessageDialog(null , "Cliente removido com sucesso!");
		} else if (pessoaJuridica.isPresent()){
			int indexPessoa = listajuridica.indexOf(pessoaJuridica.get());
			listajuridica.remove(indexPessoa);

			JOptionPane.showMessageDialog(null , "Cliente removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}
	}
	
	public static Optional<PessoaFisica> buscarPessoaFisicaPorCodigo(String codigo, List<PessoaFisica> listaClientes){
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
	}

	public static Optional<PessoaJuridica> buscarPessoaJuridicaPorCodigo(String codigo, List<PessoaJuridica> listaClientes){
		return listaClientes.stream().filter(entidade -> entidade.getCodigo().equals(codigo)).findFirst();
	}
	
	public static boolean verificaSexoPessoa(String sexo) {
		return sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F");
	}
}
