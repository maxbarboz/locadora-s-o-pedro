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

import domain.Locacao;
import domain.Pessoa;
import domain.Veiculo;
import services.LocacaoService;
import services.MenuService;
import services.PessoaService;
import services.VeiculoService;
import util.Scroll;

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
		List<Pessoa> listaPessoas = new ArrayList();
		List<Veiculo> listaVeiculos = new ArrayList();
		List<Locacao> listaLocacoes = new ArrayList();

		int opcaoMenu = 0;
		int opcaoMenuSecundario = 0;
		JOptionPane.showMessageDialog(null ,  MenuService.getMessageIntro());

		opcaoMenu = MenuService.menu();

		do {
			switch (opcaoMenu) {
			case OPTION_PESSOAS:
				opcaoMenuSecundario = MenuService.getMenuPessoas();
				switch(opcaoMenuSecundario) {
				case 1:
					PessoaService.cadastrarCliente(listaPessoas);
					JOptionPane.showMessageDialog(null , "Cliente cadastrado com sucesso!");
					break;
				case 2:
					PessoaService.listarClientes(listaPessoas);
					break;
				case 3:
					PessoaService.removerClientePorCodigo(listaPessoas);
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
					LocacaoService.cadastrarLocacao(listaLocacoes, listaPessoas, listaVeiculos);
					JOptionPane.showMessageDialog(null , "Locação cadastrada com sucesso!");
					break;
				case 2:
					LocacaoService.listarLocacao(listaLocacoes);
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


