package domain;

import java.time.LocalDate;

public class Locacao {

	private String codigo;
	private PessoaFisica pessoaFisica;
	private PessoaJurisdica pessoaJurisdica;
	private Veiculo veiculo;
	private LocalDate dataLocacao;
	private Long valorLocacao;

	public Locacao(String codigo, PessoaFisica pessoaFisica, Veiculo veiculo, LocalDate dataLocacao, Long valorLocacao) {
		this.codigo = codigo;
		this.pessoaFisica = pessoaFisica;
		this.veiculo = veiculo;
		this.dataLocacao = dataLocacao;
		this.valorLocacao = valorLocacao;
	}

	public Locacao(String codigo, PessoaJurisdica pessoaJurisdica, Veiculo veiculo, LocalDate dataLocacao, Long valorLocacao) {
		this.codigo = codigo;
		this.pessoaJurisdica = pessoaJurisdica;
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

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJurisdica getPessoaJurisdica() {
		return pessoaJurisdica;
	}

	public void setPessoaJurisdica(PessoaJurisdica pessoaJurisdica) {
		this.pessoaJurisdica = pessoaJurisdica;
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
