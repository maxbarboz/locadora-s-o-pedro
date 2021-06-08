package interfaces;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import domain.Veiculo;
import util.Scroll;

public interface VeiculoInterface {
	
	List cadastrarVeiculo(List listaVeiculos);

	void listarVeiculos(List<Veiculo> listaVeiculos);

	void removerVeiculoPorPlaca(List<Veiculo> listaVeiculos);
	
}
