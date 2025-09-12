package com.example.catalogo.dto;


import com.example.catalogo.model.Tipos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class frutasRequestDTO {

    @NotBlank @Size(max=100) private String nome;
    @NotNull private Tipos tipos;
    @NotBlank private String descricao;
    @NotBlank private String resumoPoder;
    private String usuarioAtual;
    private boolean raro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipos getTiposModel() {
        return tipos;
    }

    public void setTiposModel(Tipos Tipos) {
        this.tipos = Tipos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResumoPoder() {
        return resumoPoder;
    }

    public void setResumoPoder(String resumoPoder) {
        this.resumoPoder = resumoPoder;
    }

    public String getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(String usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public boolean isRaro() {
        return raro;
    }

    public void setRaro(boolean raro) {
        this.raro = raro;
    }
}
