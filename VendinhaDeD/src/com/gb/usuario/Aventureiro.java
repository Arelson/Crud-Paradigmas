package com.gb.usuario;

public abstract class Aventureiro {
	private int id;
	private String nome;
	private int idade;
	private double vida;
	private int nivel;
	
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
	
	public int getIdade() {
		return this.idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public double getVida() {
		return this.vida;
	}
	public void setVida(double vida) {
		this.vida = vida;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public Aventureiro(int id, String nome, int idade, double vida, int nivel) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.vida = vida;
		this.nivel = nivel;
	}
	
}
