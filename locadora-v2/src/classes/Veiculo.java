package classes;

public class Veiculo {

	private String placa;
	private String marca;
	private String nomeVeiculo;
	private char locado = 0;

	public Veiculo(String placa, String marca, String nomeVeiculo){
		this.placa = placa;
		this.marca = marca;
		this.nomeVeiculo = nomeVeiculo;
	}
	
	public Veiculo() {
		
	}

	public char getLocado() {
		return locado;
	}

	public void setLocado(char locado) {
		this.locado = locado;
	}

	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
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
