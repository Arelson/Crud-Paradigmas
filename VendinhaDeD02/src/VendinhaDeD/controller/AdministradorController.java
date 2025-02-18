package VendinhaDeD.controller;

import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;

import java.util.List;

public class AdministradorController {
    private AventureiroDAO aventureiroDAO;
    private MissaoDAO missaoDAO;

    // Injeção de dependência via construtor
    public AdministradorController() {
    	 this.aventureiroDAO = new AventureiroDAO();
         this.missaoDAO = new MissaoDAO();
    }

    // Cadastrar um novo aventureiro
    public void cadastrarAventureiro(String nome, String login, String senha, int nivel, String classe) {
        Aventureiro aventureiro = new Aventureiro(nome, login, senha, nivel, classe);
        aventureiroDAO.salvar(aventureiro); // Salva um único aventureiro
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
        System.out.println("Aventureiro não encontrado!");
    }

    // Remover um aventureiro
    public void removerAventureiro(String login) {
        aventureiroDAO.excluir(login); // Remove o aventureiro pelo login
    }

    // Cadastrar uma nova missão
    public void cadastrarMissao(String titulo, String descricao, int nivelRequerido) {
        Missao missao = new Missao(titulo, descricao, nivelRequerido);
        missaoDAO.salvar(missao); // Salva uma única missão
    }

    // Atualizar uma missão existente
    public void atualizarMissao(String titulo, String novaDescricao, int novoNivel) {
        List<Missao> missoes = missaoDAO.carregar();
        for (Missao m : missoes) {
            if (m.getTitulo().equals(titulo)) {
                m.setDescricao(novaDescricao); // Atualiza a descrição
                m.setNivelRequerido(novoNivel); // Atualiza o nível requerido
                missaoDAO.atualizar(missoes); // Atualiza a lista completa
                return;
            }
        }
        System.out.println("Missão não encontrada!");
    }

    // Remover uma missão
    public void removerMissao(String titulo) {
        missaoDAO.excluir(titulo); // Remove a missão pelo título
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