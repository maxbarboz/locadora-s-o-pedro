package domain;

public class Pessoa {

	private String codigo;
	private String nome;
	private String sexo;
	private String localidade = "Não definida";
	private String cpf;
	
	public Pessoa(String codigo, String nome, String sexo, String cpf) {
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
	}
	
	public Pessoa(String codigo, String nome, String sexo, String localidade, String cpf) {
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.localidade = localidade;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
