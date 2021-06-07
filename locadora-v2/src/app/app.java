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

		JOptionPane.showMessageDialog(null ,  MenuService.getMessageIntro());

		opcaoMenu = MenuService.menu();

		do {
			switch (opcaoMenu) {
				case OPTION_PESSOAS -> {
					opcaoMenuSecundario = MenuService.getMenuPessoas();
					switch (opcaoMenuSecundario) {
						case 1 -> {
							String tipoPessoa = PessoaService.verificarTipoPessoa();
							if (tipoPessoa.equals("1")) {
								PessoaService.cadastrarPessoaFisica(listaPessoaFisica);
							} else {
								PessoaService.cadastrarPessoaJurisdica(listaPessoaJurisdica);
							}
							JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
						}
						case 2 -> PessoaService.listarClientes(listaPessoaFisica, listaPessoaJurisdica);
						case 3 -> PessoaService.removerClientePorCodigo(listaPessoaFisica);
						case 4 -> opcaoMenu = MenuService.menu();
						case 0 -> JOptionPane.showMessageDialog(null, MESSAGE_FINALIZADO_USUARIO);
						default -> JOptionPane.showMessageDialog(null, MESSAGE_OPCAO_INVALIDA);
					}
				}
				case OPTION_VEICULOS -> {
					opcaoMenuSecundario = MenuService.getMenuVeiculos();
					switch (opcaoMenuSecundario) {
						case 1 -> {
							VeiculoService.cadastrarVeiculo(listaVeiculos);
							JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");
						}
						case 2 -> VeiculoService.listarVeiculos(listaVeiculos);
						case 3 -> VeiculoService.removerVeiculoPorPlaca(listaVeiculos);
						case 4 -> opcaoMenu = MenuService.menu();
						case 0 -> JOptionPane.showMessageDialog(null, MESSAGE_FINALIZADO_USUARIO);
						default -> JOptionPane.showMessageDialog(null, MESSAGE_OPCAO_INVALIDA);
					}
				}
				case OPTION_LOCACOES -> {
					opcaoMenuSecundario = MenuService.getMenuLocacoes();
					switch (opcaoMenuSecundario) {
						case 1 -> {
							String tipoPessoa = PessoaService.verificarTipoPessoa();
							if (tipoPessoa.equals("1")) {
								LocacaoService.cadastrarLocacao(listaLocacoes, listaPessoaFisica, listaVeiculos);
							} else {
								LocacaoService.cadastrarLocacaoPessoaJurisdica(listaLocacoes, listaPessoaJurisdica, listaVeiculos);
							}
						}
						case 2 -> {
							opcaoListagem = MenuService.getListagemLocacao();
							switch (opcaoListagem) {
								case 1 -> LocacaoService.listarLocacaoComFiltro(listaLocacoes);
								case 2 -> LocacaoService.listarLocacaoSemFiltro(listaLocacoes);
								case 0 -> JOptionPane.showMessageDialog(null, MESSAGE_FINALIZADO_USUARIO);
								default -> JOptionPane.showMessageDialog(null, MESSAGE_OPCAO_INVALIDA);
							}
						}
						case 3 -> LocacaoService.deletarLocacao(listaLocacoes);
						case 4 -> opcaoMenu = MenuService.menu();
						case 0 -> JOptionPane.showMessageDialog(null, MESSAGE_FINALIZADO_USUARIO);
						default -> JOptionPane.showMessageDialog(null, MESSAGE_OPCAO_INVALIDA);
					}
				}
				case 0 -> JOptionPane.showMessageDialog(null, MESSAGE_FINALIZADO_USUARIO);
				default -> JOptionPane.showMessageDialog(null, MESSAGE_OPCAO_INVALIDA);
			}
		} while(opcaoMenu != 0 && opcaoMenuSecundario != 0);

	}
}


