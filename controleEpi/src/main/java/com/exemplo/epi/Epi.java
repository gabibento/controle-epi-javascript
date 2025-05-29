package com.exemplo.epi;

public class Epi {
    private String nome;
    private String validade;

    public Epi(String nome, String validade) {
        this.nome = nome;
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
