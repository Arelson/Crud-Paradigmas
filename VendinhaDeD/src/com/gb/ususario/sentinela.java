package com.gb.ususario;

public class sentinela extends Aventureiro {
	
	private double distAtk;
	
	public double getDistsAtk() {
		return this.distAtk;
	}
	public void setDistAtk(double distAtk) {
		this.distAtk = distAtk;
	}
	
	public sentinela(int id, String nome, int idade, double vida, int nivel, double distAtk) {
		super(id, nome, idade, vida, nivel);
		this.distAtk = distAtk;
	}
}
