package domain;

public class PessoaJurisdica extends Pessoa {

    private String cnpj;
    private String razaoSocial;

    public PessoaJurisdica(String codigo, String localidade, String cnpj, String razaoSocial) {
        super(codigo, localidade);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public PessoaJurisdica(String codigo, String cnpj, String razaoSocial) {
        super(codigo);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
