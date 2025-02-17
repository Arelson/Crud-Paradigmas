package com.gb.usuario;

public class Sentinela extends Aventureiro {
	
	private double distAtk;
	
	public double getDistsAtk() {
		return this.distAtk;
	}
	public void setDistAtk(double distAtk) {
		this.distAtk = distAtk;
	}
	
	public Sentinela(int id, String nome, int idade, double vida, int nivel, double distAtk) {
		super(id, nome, idade, vida, nivel);
		this.distAtk = distAtk;
	}
}
