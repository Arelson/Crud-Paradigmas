package com.gb.item;

import com.gb.fornecedor.fornecedor;

public class itens {
	private int id;
	private String nome;
	private String tipo;
	private int qtd;
	private fornecedor f;
	
	public itens(int id, String nome, String tipo, int qtd, fornecedor f) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.qtd = qtd;
        this.f = f;
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

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public fornecedor getF() {
        return f;
    }
    public void setF(fornecedor f) {
        this.f = f;
    }
}
