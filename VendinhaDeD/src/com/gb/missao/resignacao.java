package com.gb.missao;
import java.util.Date;

import com.gb.ususario.Aventureiro;

public class resignacao {
	    private int id;
	    private missao missaoId; 
	    private Aventureiro aventureiroId; 
	    private String motivo;
	    private Date dataResignacao;

	    // Construtor
	    public resignacao(int id, missao missaoId, Aventureiro aventureiroId, String motivo, Date dataResignacao) {
	        this.id = id;
	        this.missaoId = missaoId;
	        this.aventureiroId = aventureiroId;
	        this.motivo = motivo;
	        this.dataResignacao = dataResignacao;
	    }


	    // Getters e Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public missao getMissaoId() {
	        return this.missaoId;
	    }

	    public void setMissaoId(missao missaoId) {
	        this.missaoId = missaoId;
	    }

	    public Aventureiro getAventureiroId() {
	        return aventureiroId;
	    }

	    public void setAventureiroId(Aventureiro aventureiroId) {
	        this.aventureiroId = aventureiroId;
	    }

	    public String getMotivo() {
	        return motivo;
	    }

	    public void setMotivo(String motivo) {
	        this.motivo = motivo;
	    }

	    public Date getDataResignacao() {
	        return dataResignacao;
	    }

	    public void setDataResignacao(Date dataResignacao) {
	        this.dataResignacao = dataResignacao;
	    }
}
