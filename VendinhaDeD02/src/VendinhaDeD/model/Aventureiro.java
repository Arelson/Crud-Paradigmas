package VendinhaDeD.model;

import java.io.Serializable;

public class Aventureiro extends usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nivel;
	private String classe;
	
	public Aventureiro(String nome, String login, String senha, int nivel, String classe) {
		super(nome, login, senha);
		this.nivel = nivel;
		this.classe = classe;
	}
	
	public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

	public void aumentarNivel() {
		this.nivel++;
	}
	
}
