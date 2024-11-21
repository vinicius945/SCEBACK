package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class EmpresaTO {
    private long IdEmpresa;
    @NotBlank private String Nome;
    @NotBlank private String CNPJ;
    @NotBlank private String Vantangens;

    public EmpresaTO() {
    }

    public EmpresaTO(long idEmpresa, String nome, String CNPJ, String vantangens) {
        IdEmpresa = idEmpresa;
        Nome = nome;
        this.CNPJ = CNPJ;
        Vantangens = vantangens;
    }

    public long getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public @NotBlank String getNome() {
        return Nome;
    }

    public void setNome(@NotBlank String nome) {
        Nome = nome;
    }

    public @NotBlank String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(@NotBlank String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public @NotBlank String getVantangens() {
        return Vantangens;
    }

    public void setVantangens(@NotBlank String vantangens) {
        Vantangens = vantangens;
    }
}
