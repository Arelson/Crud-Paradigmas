package VendinhaDeD.controller;

import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;

import java.util.List;

public class AdministradorController {
    private AventureiroDAO aventureiroDAO;
    private MissaoDAO missaoDAO;

    // Injecao de dependencia via construtor
    public AdministradorController() {
    	 this.aventureiroDAO = new AventureiroDAO();
         this.missaoDAO = new MissaoDAO();
    }
    // ---------- CRUD do aventureiro ----------
    // Cadastrar um novo aventureiro
    public void cadastrarAventureiro(String nome, String login, String senha, int nivel, String classe) {
        Aventureiro aventureiro = new Aventureiro(nome, login, senha, nivel, classe);
        aventureiroDAO.salvar(aventureiro); // Salva um �nico aventureiro
    }

    // Atualizar um aventureiro existente
    public void atualizarAventureiro(String nome, String login, String senha, int novoNivel) {
        List<Aventureiro> aventureiros = aventureiroDAO.carregar();
        for (Aventureiro a : aventureiros) {
            if (a.getLogin().equals(login)) {
                a.setNivel(novoNivel);
                a.setLogin(login);
                a.setSenha(senha);
                a.setNome(nome);
                aventureiroDAO.atualizar(aventureiros); // Atualiza a lista completa
                return;
            }
        }
        System.out.println("Aventureiro nao encontrado!");
    }

    // Remover um aventureiro
    public void removerAventureiro(String login) {
        aventureiroDAO.excluir(login); // Remove o aventureiro pelo login
    }

    
    // ---------- CRUD da missao ----------
    // Cadastrar uma nova missao
    public void cadastrarMissao(String titulo, String descricao, int nivelRequerido) {
        Missao missao = new Missao(titulo, descricao, nivelRequerido);
        missaoDAO.salvar(missao); // Salva uma unica missao
    }

    // Atualizar uma missao existente
    public void atualizarMissao(String titulo, String novaDescricao, int novoNivel) {
        List<Missao> missoes = missaoDAO.carregar();
        for (Missao m : missoes) {
            if (m.getTitulo().equals(titulo)) {
                m.setDescricao(novaDescricao); // Atualiza a descricao
                m.setNivelRequerido(novoNivel); // Atualiza o nivel requerido
                missaoDAO.atualizar(missoes); // Atualiza a lista completa
                return;
            }
        }
        System.out.println("Missao nao encontrada!");
    }

    // Remover uma missao
    public void removerMissao(String titulo) {
        missaoDAO.excluir(titulo); // Remove a missao pelo titulo
    }

    // Listar todos os aventureiros
    public List<Aventureiro> listarAventureiros() {
        return aventureiroDAO.carregar();
    }

    // Listar todas as missoes
    public List<Missao> listarMissoes() {
        return missaoDAO.carregar();
    }
}