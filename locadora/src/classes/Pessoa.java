package classes;

public class Pessoa {
	
	private String nome;
	private char sexo;
	private String localidade;
	private String cpf;
	private Long renda;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Long getRenda() {
		return renda;
	}
	
	public void setRenda(Long renda) {
		this.renda = renda;
	}

}
