package com.gb.fornecedor;

public class Alquimista extends fornecedor {
	private String espcPocao;
	private boolean hEcant;
	
	public String getEspcpocoes() {
		return this.espcPocao;
	}
	public void setEspcPocoes(String espcPocao) {
		this.espcPocao = espcPocao;
	}
	
	public boolean getHecant() {
		return this.hEcant;
	}
	public void steHecant(boolean hEcant) {
		this.hEcant = hEcant;
	}
	
	public Alquimista(int id, String nome, String cont, String espcpocao, boolean hEcant) {
		super(id, nome, cont);
		this.espcPocao = espcpocao;
		this.hEcant = hEcant;
	}
}
