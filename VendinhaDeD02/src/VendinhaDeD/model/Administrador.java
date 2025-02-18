package VendinhaDeD.model;
import VendinhaDeD.controller.AdministradorController;

public class Administrador extends usuario {
    private String chaveAcesso;
    private AdministradorController controller;

    // funcoes para alteração das informações no txt do aventureiro e missoes pelo admin
    public Administrador(String nome, String login, String senha, String chaveAcesso) {
        super(nome, login, senha);
        this.chaveAcesso = chaveAcesso;
    }

    public boolean autenticarAdministrador(String senha, String chaveAcesso) {
        return autenticar(senha) && this.chaveAcesso.equals(chaveAcesso);
    }

    public void cadastrarAventureiro(String nome, String login, String senha, int nivel, String classe) {
        controller.cadastrarAventureiro(nome, login, senha, nivel, classe);
    }

    public void removerAventureiro(String login) {
        controller.removerAventureiro(login);
    }

    public void cadastrarMissao(String titulo, String descricao, int nivelRequerido) {
        controller.cadastrarMissao(titulo, descricao, nivelRequerido);
    }

    public void removerMissao(String titulo) {
        controller.removerMissao(titulo);
    }
}

