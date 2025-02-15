package com.gb.regis;
import java.util.Date;
import com.gb.item.itens;
import com.gb.ususario.Aventureiro;

public class venda {
    private int id;
    private Aventureiro aventureiroId; // ID do Aventureiro
    private itens itemId; // ID do Item
    private int quantidade;
    private Date dataVenda;

    // Construtor
    public venda(int id, Aventureiro aventureiroId, itens itemId, int quantidade, Date dataVenda) {
        this.id = id;
        this.aventureiroId = aventureiroId;
        this.itemId = itemId;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aventureiro getAventureiroId() {
        return aventureiroId;
    }

    public void setAventureiroId(Aventureiro aventureiroId) {
        this.aventureiroId = aventureiroId;
    }

    public itens getItemId() {
        return itemId;
    }

    public void setItemId(itens itemId) {
        this.itemId = itemId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    
}
