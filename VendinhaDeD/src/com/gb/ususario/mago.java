package com.gb.ususario;

public class mago extends Aventureiro {
	
	private double magic;
	
	public double getMagic() {
		return this.magic;
	}
	public void setMagic(double magic) {
		this.magic = magic;
	}
	public mago(int id, String nome, int idade, double vida, int nivel, double magic) {
		super(id, nome, idade, vida, nivel);
		this.magic = magic;
	}
}
