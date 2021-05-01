package services;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import domain.Veiculo;
import util.Scroll;

public class VeiculoService {

	public static List cadastrarVeiculo(List listaVeiculos) {
		String placa = JOptionPane.showInputDialog(null, "Informe o placa do veículo:");
		String marca = JOptionPane.showInputDialog(null, "Informe a marca:");
		String nomeVeiculo =  JOptionPane.showInputDialog(null, "Informe o nome do veículo:");

		if(!verificaPlacaVeiculo(placa)) {
			JOptionPane.showMessageDialog(null , "Favor informar uma placa válida: 7 DIGITOS!");
			return cadastrarVeiculo(listaVeiculos);
		}
		
		if(verificaPlacaVeiculoRepetida(listaVeiculos, placa)) {
			JOptionPane.showMessageDialog(null , "Placa já cadastrada no sistema!");
			return cadastrarVeiculo(listaVeiculos);
		}
		
		if(placa.isEmpty() || marca.isEmpty() || nomeVeiculo.isEmpty()){
			JOptionPane.showMessageDialog(null , "Favor preencher todos os dados obrigatórios!");
			return cadastrarVeiculo(listaVeiculos);
		}

		listaVeiculos.add(new Veiculo(placa, marca, nomeVeiculo));
		
		return listaVeiculos;
	}
	
	public static boolean verificaPlacaVeiculoRepetida(List<Veiculo> listaVeiculos, String placa) {
		return listaVeiculos.stream().anyMatch(entidade -> entidade.getPlaca().equals(placa));
	}
	
	public static boolean verificaPlacaVeiculo(String placa) {	
		return placa.length() == 7 ? true : false;
	}

	public static void listarVeiculos(List<Veiculo> listaVeiculos) {
		StringBuilder exibicao = new StringBuilder();

		if(listaVeiculos.isEmpty()) {
			exibicao.append("\nNão existe registro para a listagem");
		} else {
			listaVeiculos.forEach(veiculo -> {
				exibicao.append("Placa: " + veiculo.getPlaca()).append("\n")
						.append("Marca: " + veiculo.getMarca()).append("\n")
						.append("Nome do veículo: " + veiculo.getNomeVeiculo()).append("\n\n");
			});
		}

		Scroll scroll = new Scroll(exibicao.toString(), "LISTAGEM DE VEÍCULOS:");
	}

	public static void removerVeiculoPorPlaca(List<Veiculo> listaVeiculos) {
		String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo a ser removido:");

		Optional<Veiculo> veiculo = listaVeiculos.stream().filter(entidade -> entidade.getPlaca().equals(placa)).findFirst();


		if(veiculo.isPresent()) {
			int indexVeiculo = listaVeiculos.indexOf(veiculo.get());

			listaVeiculos.remove(indexVeiculo);

			JOptionPane.showMessageDialog(null , "Veículo removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null , "Registro não encontrado!");
		}
	}
}
