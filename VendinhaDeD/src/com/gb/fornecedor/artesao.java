package com.gb.fornecedor;

public class artesao extends fornecedor {
	
	private String espec;
	private int Nvqld;
	
	public String getEspec() {
		return this.espec;
	}
	public void setEspec(String espc) {
		this.espec = espc;
	}
	
	public int getNvqld() {
		return this.Nvqld;
	}
	public void SetNvqld(int nvsqld) {
		this.Nvqld = nvsqld;
	}
	public artesao(int id, String nome, String cont, String espc, int Nvqld) {
		super(id, nome, cont);
		this.espec = espc;
		this.Nvqld = Nvqld;
	}
}
