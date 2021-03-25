package classes;

import java.time.LocalDate;
import java.util.Date;

public class Locacao {

	private String codigo;
	private Pessoa pessoa;
	private Veiculo veiculo;
	private LocalDate dataLocacao;
	private Long valorLocacao;

	public Locacao() {
	}

	public Locacao(String codigo, Pessoa pessoa, Veiculo veiculo, LocalDate dataLocacao, Long valorLocacao) {
		this.codigo = codigo;
		this.pessoa = pessoa;
		this.veiculo = veiculo;
		this.dataLocacao = dataLocacao;
		this.valorLocacao = valorLocacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public LocalDate getDataLocacao() {
		return dataLocacao;
	}
	
	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	
	public Long getValorLocacao() {
		return valorLocacao;
	}
	
	public void setValorLocacao(Long valorLocacao) {
		this.valorLocacao = valorLocacao;
	}
}
