package com.exemplo.epi;

public class Epi {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private int quantidade;

    public Epi(String nome, int quantidade) {
        this.id = contadorId++;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Epi.contadorId = contadorId;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
}
