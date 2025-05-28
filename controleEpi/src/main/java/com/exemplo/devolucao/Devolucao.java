package com.exemplo.devolucao;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

public class Devolucao {
    private Epi epi;
    private Usuario usuario;
    private LocalDate dataDevolucao;

    public Devolucao(Epi epi, Usuario usuario, LocalDate dataDevolucao) {
        this.epi = epi;
        this.usuario = usuario;
        this.dataDevolucao = dataDevolucao;
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