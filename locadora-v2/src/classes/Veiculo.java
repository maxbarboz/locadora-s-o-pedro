package classes;

public class Veiculo {

	private String placa;
	private String marca;
	private String nomeVeiculo;
	private Long quilometragem;
	private char locado;
	
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
	
	public Long getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}
}