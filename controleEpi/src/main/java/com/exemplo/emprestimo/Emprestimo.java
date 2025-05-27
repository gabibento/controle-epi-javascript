package com.exemplo.emprestimo;

import com.exemplo.epi.Epi;
import com.exemplo.usuario.Usuario;

import java.time.LocalTime;

public class Emprestimo {
    private Epi epi;
    private Usuario usuario;
    private LocalTime dataEmprestimo;
    private LocalTime dataDevolucao;

    public Epi getEpi() {
        return epi;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalTime getDataDevolucao() {
        return dataDevolucao;
    }
}