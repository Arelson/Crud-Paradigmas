package VendinhaDeD.model;

import java.io.Serializable;

public class Missao implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
    private String descricao;
    private int nivelRequerido;

    public Missao(String titulo, String descricao, int nivelRequerido) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivelRequerido = nivelRequerido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivelRequerido() {
        return nivelRequerido;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public void setNivelRequerido(int nivelr) {
        this.nivelRequerido = nivelr;
    }

    public boolean podeParticipar(Aventureiro aventureiro) {
        return aventureiro.getNivel() >= this.nivelRequerido;
    }
}

