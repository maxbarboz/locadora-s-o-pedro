package domain;

public class PessoaFisica extends Pessoa{

    private String cpf;
    private String nome;
    private String sexo;

    public PessoaFisica(String codigo, String localidade, String cpf, String nome, String sexo) {
        super(codigo, localidade);
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
    }

    public PessoaFisica(String codigo, String cpf, String nome, String sexo) {
        super(codigo);
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
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

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
