package com.gb.servicoUsuario;

import java.util.ArrayList;
import java.util.List;

import com.gb.usuario.Aventureiro;
import com.gb.usuario.Guerreiro;
import com.gb.usuario.Mago;
import com.gb.usuario.Sentinela;

public class ServicoUsuario {
    private List<Aventureiro> aventureiros = new ArrayList<>();
    private int proximoId = 1;

    // CREATE: Adicionar um novo aventureiro do tipo guerreiro
    public void criaGuerreiro(int Id, String nome, int idade, double vida, int nivel, double defesaPassiva) {
        Id = proximoId;
        Aventureiro aventureiro = new Guerreiro(proximoId++, nome, idade,  vida, nivel, defesaPassiva);
        aventureiros.add(aventureiro);
        System.out.println("Aventureiro criado: " + aventureiro);
    }
    // CREATE: Adicionar um novo aventureiro do tipo Mago
    public void criaMago(int Id, String nome, int idade, double vida, int nivel, double magic) {
        Id = proximoId;
        Aventureiro aventureiro = new Mago(proximoId++, nome, idade,  vida, nivel, magic);
        aventureiros.add(aventureiro);
        System.out.println("Aventureiro criado: " + aventureiro);
    }
    // CREATE: Adicionar um novo aventureiro do tipo Sentinela
    public void criaSentinela(int Id, String nome, int idade, double vida, int nivel, double distAtk) {
        Id = proximoId;
        Aventureiro aventureiro = new Sentinela(proximoId++, nome, idade,  vida, nivel, distAtk);
        aventureiros.add(aventureiro);
        System.out.println("Aventureiro criado: " + aventureiro);
    }
    

    // READ: Listar todos os aventureiros
    public void listarAventureiro() {
        if (aventureiros.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Lista de Usuários:");
            for (Aventureiro aventureiro : aventureiros) {
                System.out.println(aventureiro);
            }
        }
    }

    // UPDATE: Atualizar um aventureiro pelo ID
    public void atualizarAventureiro(int id, String novoNome) {
        for (Aventureiro aventureiros : aventureiros) {
            if (aventureiros.getId() == id) {
                aventureiros.setNome(novoNome);
                System.out.println("Aventureiro atualizado: " + aventureiros);
                return;
            }
        }
        System.out.println("Aventureiro com ID " + id + " não encontrado.");
    }

    // DELETE: Remover um aventureiro pelo ID
    public void deletarUsuario(int id) {
        aventureiros.removeIf(aventureiros -> aventureiros.getId() == id);
        System.out.println("Aventureiro com ID " + id + " removido.");
    }
}