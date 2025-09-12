package com.example.catalogo.dto;

import com.example.catalogo.model.Tipos;
import com.example.catalogo.model.frutasModel;
import java.time.LocalDateTime;

public class frutasResponseDTO {
    private Long id;
    private String nome;
    private frutasModel tipo;
    private String descricao;
    private String resumopoder;
    private String usuarioAtual;
    private String usuarioAnterior;
    private LocalDateTime dataDescoberta;
    private boolean raro;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public frutasModel getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResumopoder() {
        return resumopoder;
    }

    public void setResumopoder(String resumopoder) {
        this.resumopoder = resumopoder;
    }

    public String getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(String usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public String getUsuarioAnterior() {
        return usuarioAnterior;
    }

    public void setUsuarioAnterior(String usuarioAnterior) {
        this.usuarioAnterior = usuarioAnterior;
    }

    public LocalDateTime getDataDescoberta() {
        return dataDescoberta;
    }

    public void setDataDescoberta(LocalDateTime dataDescoberta) {
        this.dataDescoberta = dataDescoberta;
    }

    public boolean isRaro() {
        return raro;
    }

    public void setRaro(boolean raro) {
        this.raro = raro;
    }
}
