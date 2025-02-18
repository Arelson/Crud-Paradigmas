package VendinhaDeD.view;

import VendinhaDeD.controller.AdministradorController;
import VendinhaDeD.view.GerenciarAventureirosView;
import javax.swing.*;

public class AdminView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnGerenciarAventureiros;
    private JButton btnGerenciarMissoes;
    private JButton btnSair;
    //private AdministradorController controller;

    public AdminView() {
        //controller = new AdministradorController();
        
        setTitle("Painel do Administrador");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        btnGerenciarAventureiros = new JButton("Gerenciar Aventureiros");
        btnGerenciarMissoes = new JButton("Gerenciar Missões");
        btnSair = new JButton("Sair");

        add(btnGerenciarAventureiros);
        add(btnGerenciarMissoes);
        add(btnSair);

        // Eventos
        btnGerenciarAventureiros.addActionListener(e -> new GerenciarAventureirosView().setVisible(true));
        btnGerenciarMissoes.addActionListener(e -> new GerenciarMissoesView().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }
}
