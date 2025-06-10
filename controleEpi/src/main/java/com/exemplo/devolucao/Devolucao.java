package com.exemplo.devolucao;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;

import java.time.LocalDate;

public class Devolucao {
    private static int contadorId = 1;
    private int id;
    private Epi epi;
    private Usuario usuario;
    private LocalDate dataDevolucao;


    public Devolucao(Epi epi, Usuario usuario, LocalDate dataDevolucao) {
        this.id = contadorId ++;
        this.epi = epi;
        this.usuario = usuario;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Epi getEpi() {
        return epi;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}