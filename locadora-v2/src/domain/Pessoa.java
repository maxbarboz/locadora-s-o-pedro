package domain;

public class Pessoa {

	private String codigo;
	private String localidade = "Não definida";

	public Pessoa(String codigo, String localidade) {
		this.codigo = codigo;
		this.localidade = localidade;
	}

	public Pessoa(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
}
