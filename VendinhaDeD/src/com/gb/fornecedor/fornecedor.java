package com.gb.fornecedor;

public class fornecedor {
	private int id;
	private String nome;
	private String cont;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCont() {
		return this.cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	public fornecedor(int id, String nome, String cont) {
		this.id = id;
		this.nome = nome;
		this.cont = cont;
	}
}

