package classes;

import java.util.Date;

public class Locacao {

	private Pessoa pessoa;
	private Veiculo veiculo;
	private Date dataLocacao;
	private Long valorLocacao;
	
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
	
	public Date getDataLocacao() {
		return dataLocacao;
	}
	
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	
	public Long getValorLocacao() {
		return valorLocacao;
	}
	
	public void setValorLocacao(Long valorLocacao) {
		this.valorLocacao = valorLocacao;
	}
}
