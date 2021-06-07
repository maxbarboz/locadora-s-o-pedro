package app;

import domain.Locacao;
import domain.PessoaFisica;
import domain.PessoaJurisdica;
import domain.Veiculo;
import services.LocacaoService;
import services.MenuService;
import services.PessoaService;
import services.VeiculoService;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class app {

	public static void main(String[] args) {

		/* Opções do menu principal */
		final int OPTION_PESSOAS = 1;
		final int OPTION_VEICULOS = 2;
		final int OPTION_LOCACOES = 3;
		
		/* Messagens padrões do sistema */
		final String MESSAGE_FINALIZADO_USUARIO = "Programa finalizado pelo usuário!";
		final String MESSAGE_OPCAO_INVALIDA = "Opção não encontrada, favor inserir valor válido!";
		
		/* Listas onde serão salvas os registros */
		List<PessoaFisica> listaPessoaFisica = new ArrayList();
		List<PessoaJurisdica> listaPessoaJurisdica = new ArrayList();
		List<Veiculo> listaVeiculos = new ArrayList();
		List<Locacao> listaLocacoes = new ArrayList();

		int opcaoMenu = 0;
		int opcaoMenuSecundario = 0;
		int opcaoListagem = 0;
		String tipoPessoa = "";
		
		JOptionPane.showMessageDialog(null ,  MenuService.getMessageIntro());

		opcaoMenu = MenuService.menu();

		do {
			if(opcaoMenu != 0){
				tipoPessoa = PessoaService.verificarTipoPessoa();
			}
			switch (opcaoMenu) {
			case OPTION_PESSOAS:
				opcaoMenuSecundario = MenuService.getMenuPessoas();
				switch(opcaoMenuSecundario) {
				case 1:
					if(tipoPessoa.equals("1")){
						PessoaService.cadastrarPessoaFisica(listaPessoaFisica);
					} else {
						PessoaService.cadastrarPessoaJurisdica(listaPessoaJurisdica);
					}

					JOptionPane.showMessageDialog(null , "Cliente cadastrado com sucesso!");
					break;
				case 2:
					PessoaService.listarClientes(listaPessoaFisica, listaPessoaJurisdica);
					break;
				case 3:
					PessoaService.removerClientePorCodigo(listaPessoaFisica);
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
					VeiculoService.cadastrarVeiculo(listaVeiculos);
					JOptionPane.showMessageDialog(null , "Veiculo cadastrado com sucesso!");
					break;
				case 2:
					VeiculoService.listarVeiculos(listaVeiculos);
					break;
				case 3:
					VeiculoService.removerVeiculoPorPlaca(listaVeiculos);
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
					if(tipoPessoa.equals("1")){
						LocacaoService.cadastrarLocacao(listaLocacoes, listaPessoaFisica, listaVeiculos);
					} else {
						LocacaoService.cadastrarLocacaoPessoaJurisdica(listaLocacoes, listaPessoaJurisdica, listaVeiculos);
					}
					break;
				case 2:
					opcaoListagem = MenuService.getListagemLocacao();
					switch(opcaoListagem) {
					case 1:
						LocacaoService.listarLocacaoComFiltro(listaLocacoes);
						break;
					case 2:
						LocacaoService.listarLocacaoSemFiltro(listaLocacoes);
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
					LocacaoService.deletarLocacao(listaLocacoes);
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


