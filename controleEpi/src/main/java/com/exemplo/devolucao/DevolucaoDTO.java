package com.exemplo.devolucao;

import java.time.LocalDate;

public class DevolucaoDTO {
    private String nomeEpi;
    private String emailUsuario;
    private LocalDate dataDevolucao;

      public DevolucaoDTO(String nomeEpi, String emailUsuario, LocalDate dataDevolucao) {
        this.nomeEpi = nomeEpi;
        this.emailUsuario = emailUsuario;
        this.dataDevolucao = dataDevolucao;
    }

    public String getNomeEpi() {
        return nomeEpi;
    }

    public void setNomeEpi(String nomeEpi) {
        this.nomeEpi = nomeEpi;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
