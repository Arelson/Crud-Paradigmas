package VendinhaDeD.controller;

import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;

import java.util.List;

public class AdministradorController {
    private AventureiroDAO aventureiroDAO;
    private MissaoDAO missaoDAO;

    // Inje��o de depend�ncia via construtor
    public AdministradorController() {
    	 this.aventureiroDAO = new AventureiroDAO();
         this.missaoDAO = new MissaoDAO();
    }

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
        System.out.println("Aventureiro n�o encontrado!");
    }

    // Remover um aventureiro
    public void removerAventureiro(String login) {
        aventureiroDAO.excluir(login); // Remove o aventureiro pelo login
    }

    // Cadastrar uma nova miss�o
    public void cadastrarMissao(String titulo, String descricao, int nivelRequerido) {
        Missao missao = new Missao(titulo, descricao, nivelRequerido);
        missaoDAO.salvar(missao); // Salva uma �nica miss�o
    }

    // Atualizar uma miss�o existente
    public void atualizarMissao(String titulo, String novaDescricao, int novoNivel) {
        List<Missao> missoes = missaoDAO.carregar();
        for (Missao m : missoes) {
            if (m.getTitulo().equals(titulo)) {
                m.setDescricao(novaDescricao); // Atualiza a descri��o
                m.setNivelRequerido(novoNivel); // Atualiza o n�vel requerido
                missaoDAO.atualizar(missoes); // Atualiza a lista completa
                return;
            }
        }
        System.out.println("Miss�o n�o encontrada!");
    }

    // Remover uma miss�o
    public void removerMissao(String titulo) {
        missaoDAO.excluir(titulo); // Remove a miss�o pelo t�tulo
    }

    // Listar todos os aventureiros
    public List<Aventureiro> listarAventureiros() {
        return aventureiroDAO.carregar();
    }

    // Listar todas as miss�es
    public List<Missao> listarMissoes() {
        return missaoDAO.carregar();
    }
}