package VendinhaDeD.controller;

import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;

import java.util.List;

public class AdministradorController {
    private AventureiroDAO aventureiroDAO;
    private MissaoDAO missaoDAO;

    public AdministradorController() {
        this.aventureiroDAO = new AventureiroDAO();
        this.missaoDAO = new MissaoDAO();
    }

    // Cadastrar um novo aventureiro
    public void cadastrarAventureiro(String nome, String login, String senha, int nivel, String classe) {
        Aventureiro aventureiro = new Aventureiro(nome, login, senha, nivel, classe);
        List<Aventureiro> aventureiros = aventureiroDAO.carregar();
        aventureiros.add(aventureiro);
        aventureiroDAO.salvar(aventureiros);
    }

    // Atualizar um aventureiro existente
    public void atualizarAventureiro(String login, int novoNivel) {
        List<Aventureiro> aventureiros = aventureiroDAO.carregar();
        for (Aventureiro a : aventureiros) {
            if (a.getLogin().equals(login)) {
                a.aumentarNivel();
                aventureiroDAO.salvar(aventureiros);
                return;
            }
        }
        System.out.println("Aventureiro não encontrado!");
    }

    // Remover um aventureiro
    public void removerAventureiro(String login) {
        List<Aventureiro> aventureiros = aventureiroDAO.carregar();
        aventureiros.removeIf(a -> a.getLogin().equals(login));
        aventureiroDAO.salvar(aventureiros);
    }

    // Cadastrar uma nova missão
    public void cadastrarMissao(String titulo, String descricao, int nivelRequerido) {
        Missao missao = new Missao(titulo, descricao, nivelRequerido);
        List<Missao> missoes = missaoDAO.carregar();
        missoes.add(missao);
        missaoDAO.salvar(missoes);
    }

    // Atualizar uma missão existente
    public void atualizarMissao(String titulo, String novaDescricao, int novoNivel) {
        List<Missao> missoes = missaoDAO.carregar();
        for (Missao m : missoes) {
            if (m.getTitulo().equals(titulo)) {
                missoes.remove(m);
                missoes.add(new Missao(titulo, novaDescricao, novoNivel));
                missaoDAO.salvar(missoes);
                return;
            }
        }
        System.out.println("Missão não encontrada!");
    }

    // Remover uma missão
    public void removerMissao(String titulo) {
        List<Missao> missoes = missaoDAO.carregar();
        missoes.removeIf(m -> m.getTitulo().equals(titulo));
        missaoDAO.salvar(missoes);
    }

    // Listar todos os aventureiros
    public List<Aventureiro> listarAventureiros() {
        return aventureiroDAO.carregar();
    }

    // Listar todas as missões
    public List<Missao> listarMissoes() {
        return missaoDAO.carregar();
    }
}
