package com.gb.usuario;

public class Guerreiro extends Aventureiro {
	private double defesaPassiva;
	
	public double getDefesaPassiva() {
		return this.defesaPassiva;
	}
	public void setDefesaPassiva(double defesaPassiva) {
		this.defesaPassiva = defesaPassiva;
	}
	public Guerreiro(int id, String nome, int idade, double vida, int nivel, double defesaPassiva) {
		super(id, nome, idade, vida, nivel);
		this.defesaPassiva = defesaPassiva;
	}
}
