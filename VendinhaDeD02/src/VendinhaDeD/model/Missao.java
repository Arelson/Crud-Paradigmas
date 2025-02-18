package VendinhaDeD.model;

public class Missao {
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

    public boolean podeParticipar(Aventureiro aventureiro) {
        return aventureiro.getNivel() >= this.nivelRequerido;
    }
}

