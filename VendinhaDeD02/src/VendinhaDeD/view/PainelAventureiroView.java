package VendinhaDeD.view;

import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;

public class PainelAventureiroView extends JFrame {
    private static final long serialVersionUID = 1L;
    private Aventureiro aventureiro;
    private MissaoDAO missaoDAO;
    private DefaultListModel<String> listaMissoesModel;
    private JList<String> listaMissoes;
    private JButton btnVoltar;

    public PainelAventureiroView(Aventureiro aventureiro) {
        this.aventureiro = aventureiro;
        this.missaoDAO = new MissaoDAO();
        setTitle("Painel do Aventureiro - " + aventureiro.getNome());
        setSize(500, 500); // Aumenta o tamanho para melhor distribui��o
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        URL iconURL = getClass().getResource("/icon1.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        } else {
            System.err.println("�cone n�o encontrado! Certifique-se de que 'icon1.png' est� na pasta correta.");
        }

        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Painel de informa��es do aventureiro
        JPanel painelInfo = new JPanel(new GridLayout(3, 1));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informa��es do aventureiro"));
        painelInfo.add(new JLabel("Nome: " + aventureiro.getNome()));
        painelInfo.add(new JLabel("Classe: " + aventureiro.getClasse()));
        painelInfo.add(new JLabel("N�vel: " + aventureiro.getNivel()));

        // Ajusta tamanho do painel de informa��es
        painelInfo.setPreferredSize(new Dimension(400, 80));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(painelInfo, gbc);

        // Lista de miss�es dispon�veis
        listaMissoesModel = new DefaultListModel<>();
        carregarMissoes();
        listaMissoes = new JList<>(listaMissoesModel);
        listaMissoes.setFixedCellHeight(25); // Aumenta altura das c�lulas para evitar corte
        listaMissoes.setVisibleRowCount(5); // Garante mais espa�o para visualiza��o
        listaMissoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaMissoes);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Permite expans�o vertical
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Bot�o para escolher miss�o
        JButton btnEscolherMissao = new JButton("Escolher Miss�o");
        btnEscolherMissao.addActionListener((ActionEvent e) -> escolherMissao());

        // Bot�o para voltar � tela de login
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener((ActionEvent e) -> voltarParaLogin());

        // Painel para os bot�es
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        painelBotoes.add(btnEscolherMissao);
        painelBotoes.add(btnVoltar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0; // N�o precisa crescer
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(painelBotoes, gbc);
    }

    private void carregarMissoes() {
        List<Missao> missoes = missaoDAO.carregar();
        listaMissoesModel.clear();
        for (Missao m : missoes) {
            listaMissoesModel.addElement(m.getTitulo() + " - N�vel Requerido: " + m.getNivelRequerido());
        }
    }

    private void escolherMissao() {
        int index = listaMissoes.getSelectedIndex();
        if (index != -1) {
            List<Missao> missoes = missaoDAO.carregar();
            Missao missaoEscolhida = missoes.get(index);

            if (aventureiro.getNivel() >= missaoEscolhida.getNivelRequerido()) {
                JOptionPane.showMessageDialog(this, aventureiro.getNome() + " escolheu a miss�o:\n" + missaoEscolhida.getTitulo() + "\n" + "Descri��o:\n" + missaoEscolhida.getDescricao());
            } else {
                JOptionPane.showMessageDialog(this, "N�vel insuficiente para escolher esta miss�o.\nN�vel requerido: " + missaoEscolhida.getNivelRequerido());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma miss�o antes de continuar.");
        }
    }

    private void voltarParaLogin() {
        this.dispose();
        new LoginView().setVisible(true);
    }
}