package util;

import javax.swing.JOptionPane;

public class Menu {

	public static int menu () {

		try {
			String menu = "Informe a opção que deseja:"+
					"\n\n[1] - CLIENTES"+
					"\n[2] - VEICULOS"+
					"\n[3] - LOCAÇÕES"+
					"\n\n[0] - Sair do programa ";
			int opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
			return opc;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null , "Atenção: Digite valores númericos de 0 a 5 de acordo com as opções.\nObrigado!");
			return menu(); // chama o menu se o erro for inserção de caracteres ou valor nulo
		// REVER 
		}
	}
	
	public static int getMenuPessoas() {
		int opc = 0;
		try {
			String menu = "Informe a opção que deseja referente a CLIENTES:"+
					"\n\n[1] - Cadastrar"+
					"\n[2] - Listar"+
					"\n[3] - Deletar"+
					"\n\n[0] - Sair do programa ";
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
			return opc;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null , "Atenção: Digite valores númericos de 0 a 3 de acordo com as opções do menu.\n\nObrigado!!");
			return opc = getMenuPessoas(); // chama o menu se o erro for inserção de caracteres ou valor nulo
		// REVER 
		}
	}
	
	public static int getMenuVeiculos() {
		int opc = 0;
		try {
			String menu = "Informe a opção que deseja referente a VEICULOS:"+
					"\n\n[1] - Cadastrar"+
					"\n[2] - Listar"+
					"\n[3] - Deletar"+
					"\n\n[0] - Sair do programa ";
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
			return opc;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null , "Atenção: Digite valores númericos de 0 a 3 de acordo com as opções do menu.\n\nObrigado!!");
			return opc = getMenuVeiculos(); // chama o menu se o erro for inserção de caracteres ou valor nulo
		// REVER 
		}
	}
	
	public static int getMenuLocacoes() {
		int opc = 0;
		try {
			String menu = "Informe a opção que deseja referente a LOCAÇÕES:"+
					"\n\n[1] - Cadastrar"+
					"\n[2] - Listar"+
					"\n[3] - Deletar"+
					"\n\n[0] - Sair do programa ";
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
			return opc;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null , "Atenção: Digite valores númericos de 0 a 3 de acordo com as opções do menu.\n\nObrigado!!");
			return opc = getMenuLocacoes(); // chama o menu se o erro for inserção de caracteres ou valor nulo
		// REVER 
		}
	}
	
	public static String getMessageIntro() {
		StringBuilder intro = new StringBuilder();
		
		intro.append("Projeto feito com princípio de estudo de Collections, Iterator e sobrecarga de métodos. Disciplina de Linguagens de Programação 1.")
			.append("\n\n\nO sistema cuidara das funções de registros de locações:")
			.append("\n- Cadastro/Registro de locação")
			.append("\n- Exclusão de uma locação")
			.append("\n- Listagem de locações")
			.append("\n- Listagem de locações com filtro")
			.append("\n\nDentre outras funções.")
			.append("\n\n\nobs.: Importante lembrar que o JoptionPane nos fornece uma interface bem simples, com algumas inconsistências.")
			.append("\n\n\nGrupo formado por: Maxsuel Storch, Elias Machado e Matheus Strutz.");
		
		return intro.toString();
	}
}
