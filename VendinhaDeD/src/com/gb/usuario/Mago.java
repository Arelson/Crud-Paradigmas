package com.gb.usuario;

public class Mago extends Aventureiro {
	
	private double magic;
	
	public double getMagic() {
		return this.magic;
	}
	public void setMagic(double magic) {
		this.magic = magic;
	}
	public Mago(int id, String nome, int idade, double vida, int nivel, double magic) {
		super(id, nome, idade, vida, nivel);
		this.magic = magic;
	}
}
