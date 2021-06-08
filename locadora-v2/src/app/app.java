package app;

import domain.Locacao;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import domain.Veiculo;
import services.LocacaoService;
import services.MenuService;
import services.PessoaService;
import services.VeiculoService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class app {
	
	public static void main(String[] args) {
		
		/* Service */
		PessoaService pessoaService = new PessoaService();
		VeiculoService veiculoService = new VeiculoService();
		LocacaoService locacaoService = new LocacaoService();
		
		/* Opções do menu principal */
		final int OPTION_PESSOAS = 1;
		final int OPTION_VEICULOS = 2;
		final int OPTION_LOCACOES = 3;
		
		/* Messagens padrões do sistema */
		final String MESSAGE_FINALIZADO_USUARIO = "Programa finalizado pelo usuário!";
		final String MESSAGE_OPCAO_INVALIDA = "Opção não encontrada, favor inserir valor válido!";
		
		/* Listas onde serão salvas os registros */
		List<PessoaFisica> listaPessoaFisica = new ArrayList();
		List<PessoaJuridica> listaPessoaJuridica = new ArrayList();
		List<Veiculo> listaVeiculos = new ArrayList();
		List<Locacao> listaLocacoes = new ArrayList();

		int opcaoMenu = 0;
		int opcaoMenuSecundario = 0;
		int opcaoListagem = 0;

		JOptionPane.showMessageDialog(null ,  MenuService.getMessageIntro());

		opcaoMenu = MenuService.menu();

		do {
			switch (opcaoMenu) {
			case OPTION_PESSOAS:
				opcaoMenuSecundario = MenuService.getMenuPessoas();
				switch(opcaoMenuSecundario) {
				case 1:
					String tipoPessoa = pessoaService.verificarTipoPessoa();
					if(tipoPessoa.equals("1")){
						pessoaService.cadastrarPessoaFisica(listaPessoaFisica);
					} else {
						pessoaService.cadastrarPessoaJuridica(listaPessoaJuridica);
					}

					JOptionPane.showMessageDialog(null , "Cliente cadastrado com sucesso!");
					break;
				case 2:
					pessoaService.listarClientes(listaPessoaFisica, listaPessoaJuridica);
					break;
				case 3:
					pessoaService.removerClientePorCodigo(listaPessoaFisica, listaPessoaJuridica);
					break;
				case 4:
					opcaoMenu = MenuService.menu();
					break;
				case 0:
					JOptionPane.showMessageDialog(null , MESSAGE_FINALIZADO_USUARIO);
					break;
				default:
					JOptionPane.showMessageDialog(null , MESSAGE_OPCAO_INVALIDA);
					break;
				}
				break;
			case OPTION_VEICULOS:
				opcaoMenuSecundario = MenuService.getMenuVeiculos();
				switch(opcaoMenuSecundario) {
				case 1:
					veiculoService.cadastrarVeiculo(listaVeiculos);
					JOptionPane.showMessageDialog(null , "Veiculo cadastrado com sucesso!");
					break;
				case 2:
					veiculoService.listarVeiculos(listaVeiculos);
					break;
				case 3:
					veiculoService.removerVeiculoPorPlaca(listaVeiculos);
					break;
				case 4:
					opcaoMenu = MenuService.menu();
					break;
				case 0:
					JOptionPane.showMessageDialog(null , MESSAGE_FINALIZADO_USUARIO);
					break;
				default:
					JOptionPane.showMessageDialog(null , MESSAGE_OPCAO_INVALIDA);
					break;
				}
				break;
			case OPTION_LOCACOES:
				opcaoMenuSecundario = MenuService.getMenuLocacoes();
				switch(opcaoMenuSecundario) {
				case 1:
					String tipoPessoa = pessoaService.verificarTipoPessoa();
					if(tipoPessoa.equals("1")){
						locacaoService.cadastrarLocacao(listaLocacoes, listaPessoaFisica, listaVeiculos);
					} else {
						locacaoService.cadastrarLocacaoPessoaJuridica(listaLocacoes, listaPessoaJuridica, listaVeiculos);
					}
					break;
				case 2:
					opcaoListagem = MenuService.getListagemLocacao();
					switch(opcaoListagem) {
					case 1:
						locacaoService.listarLocacaoComFiltro(listaLocacoes);
						break;
					case 2:
						locacaoService.listarLocacaoSemFiltro(listaLocacoes);
						break;
					case 0:
						JOptionPane.showMessageDialog(null , MESSAGE_FINALIZADO_USUARIO);
						break;
					default:
						JOptionPane.showMessageDialog(null , MESSAGE_OPCAO_INVALIDA);
						break;
					}
					break;
				case 3:
					locacaoService.deletarLocacao(listaLocacoes);
					break;
				case 4:
					opcaoMenu = MenuService.menu();
					break;
				case 0:
					JOptionPane.showMessageDialog(null , MESSAGE_FINALIZADO_USUARIO);
					break;
				default:
					JOptionPane.showMessageDialog(null , MESSAGE_OPCAO_INVALIDA);
					break;
				}
				break;
			case 0:
				JOptionPane.showMessageDialog(null , MESSAGE_FINALIZADO_USUARIO);
				break;
			default:
				JOptionPane.showMessageDialog(null , MESSAGE_OPCAO_INVALIDA);
				break;
			}
		} while(opcaoMenu != 0 && opcaoMenuSecundario != 0);

	}
}


