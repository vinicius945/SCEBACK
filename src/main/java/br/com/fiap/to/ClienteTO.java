package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class ClienteTO {
    private Long IdCliente;
    @NotBlank private String Nome;
    @NotBlank private String email;
    @NotBlank private String genero;

    public ClienteTO() {}

    public ClienteTO(Long idCliente, String nome, String email, String genero) {
        IdCliente = idCliente;
        Nome = nome;
        this.email = email;
        this.genero = genero;
    }

    public Long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Long idCliente) {
        IdCliente = idCliente;
    }

    public @NotBlank String getNome() {
        return Nome;
    }

    public void setNome(@NotBlank String nome) {
        Nome = nome;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getGenero() {
        return genero;
    }

    public void setGenero(@NotBlank String genero) {
        this.genero = genero;
    }
}
