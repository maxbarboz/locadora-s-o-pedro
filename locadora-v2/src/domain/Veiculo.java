package domain;

public class Veiculo {

	private String placa;
	private String marca;
	private String nomeVeiculo;

	public Veiculo(String placa, String marca, String nomeVeiculo){
		this.placa = placa;
		this.marca = marca;
		this.nomeVeiculo = nomeVeiculo;
	}

	public String getPlaca() {
		return placa;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getNomeVeiculo() {
		return nomeVeiculo;
	}
	
	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}
}
