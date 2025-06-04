package com.exemplo.epi;

public class Epi {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String validade;

    public Epi(String nome, String validade) {
        this.id = contadorId++;
        this.nome = nome;
        this.validade = validade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
