package com.gb.missao;

public class missao {
	    private int id;
	    private String nome;
	    private String descricao;
	    private String dificuldade;
	    private double recompensa;
	    private String status;

	    // Construtor
	    public missao(int id, String nome, String descricao, String dificuldade, double recompensa, String status) {
	        this.id = id;
	        this.nome = nome;
	        this.descricao = descricao;
	        this.dificuldade = dificuldade;
	        this.recompensa = recompensa;
	        this.status = status;
	    }

	    // Getters e Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public String getDificuldade() {
	        return dificuldade;
	    }

	    public void setDificuldade(String dificuldade) {
	        this.dificuldade = dificuldade;
	    }

	    public double getRecompensa() {
	        return recompensa;
	    }

	    public void setRecompensa(double recompensa) {
	        this.recompensa = recompensa;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
}

