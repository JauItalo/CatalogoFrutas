package com.example.catalogo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "frutas")
public class frutasModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipos tipos;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "resumo_poder")
    private String resumoPoder;

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

    public Tipos getTipos() {
        return tipos;
    }

    public void setTipos(Tipos tipos) {
        this.tipos = tipos;
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
