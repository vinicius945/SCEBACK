package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class AvaliacaoTO {
    private long IdAvaliacao;
    @NotBlank private String titulo;
    @NotNull  private Integer nota;
    private String comentario;
    @NotNull private long idCliente;
    @NotNull private long idEmpresa;

    public AvaliacaoTO() { }

    public AvaliacaoTO(long idAvaliacao, String titulo, Integer nota, String comentario, long idCliente, long idEmpresa) {
        IdAvaliacao = idAvaliacao;
        this.titulo = titulo;
        this.nota = nota;
        this.comentario = comentario;
        this.idCliente = idCliente;
        this.idEmpresa = idEmpresa;
    }

    public long getIdAvaliacao() {
        return IdAvaliacao;
    }

    public void setIdAvaliacao(long idAvaliacao) {
        IdAvaliacao = idAvaliacao;
    }

    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotNull Integer getNota() {
        return nota;
    }

    public void setNota(@NotNull Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @NotNull
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(@NotNull long idCliente) {
        this.idCliente = idCliente;
    }

    @NotNull
    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(@NotNull long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
