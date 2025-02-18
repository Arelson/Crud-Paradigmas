package VendinhaDeD.view;

import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;
import VendinhaDeD.dao.MissaoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AventureiroView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aventureiro aventureiro;
    private MissaoDAO missaoDAO;
    private JComboBox<String> cbMissoes;
    private JButton btnParticipar;
    private JButton btnSair;

    public AventureiroView(Aventureiro aventureiro, MissaoDAO missaoDAO) {
        this.aventureiro = aventureiro;
        this.missaoDAO = missaoDAO;

        setTitle("Escolha sua Missao");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        cbMissoes = new JComboBox<>();
        carregarMissoes();

        btnParticipar = new JButton("Participar");
        btnSair = new JButton("Sair");

        add(cbMissoes);
        add(btnParticipar);
        add(btnSair);

        // Evento de Participar
        btnParticipar.addActionListener(e -> participarMissao());

        // Evento de Sair
        btnSair.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    private void carregarMissoes() {
        List<Missao> missoes = missaoDAO.carregar();
        for (Missao missao : missoes) {
            if (missao.podeParticipar(aventureiro)) {
                cbMissoes.addItem(missao.getTitulo());
            }
        }
        if (cbMissoes.getItemCount() == 0) {
            cbMissoes.addItem("Nenhuma missao disponivel.");
        }
    }

    private void participarMissao() {
        String missaoSelecionada = (String) cbMissoes.getSelectedItem();
        if (missaoSelecionada == null || missaoSelecionada.equals("Nenhuma missao disponivel.")) {
            JOptionPane.showMessageDialog(this, "Nenhuma missao disponivel para seu nivel.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, aventureiro.getNome() + " iniciou a missao: " + missaoSelecionada + "!", "Missao Aceita", JOptionPane.INFORMATION_MESSAGE);
    }
}
