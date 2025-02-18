package VendinhaDeD.view;

import javax.swing.*;

public class AdminView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton btnGerenciarAventureiros;
    private JButton btnGerenciarMissoes;
    private JButton btnSair;
    private JButton btnVolta;

    public AdminView() {
        setTitle("Painel do Administrador");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        btnGerenciarAventureiros = new JButton("Gerenciar Aventureiros");
        btnGerenciarMissoes = new JButton("Gerenciar Missões");
        btnVolta = new JButton("Voltar");
        btnSair = new JButton("Sair");

        add(btnGerenciarAventureiros);
        add(btnGerenciarMissoes);
        add(btnVolta);
        add(btnSair);

        // Eventos
        btnGerenciarAventureiros.addActionListener(e -> new GerenciarAventureirosView().setVisible(true));
        btnGerenciarMissoes.addActionListener(e -> new GerenciarMissoesView().setVisible(true));
        btnVolta.addActionListener(e -> voltarParaLogin());
        btnSair.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    private void voltarParaLogin() {
        // Fecha a janela atual (AdminView)
        this.dispose();
        
        // Abre a tela de login (LoginView)
        new LoginView().setVisible(true);
    }
}