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
        setSize(500, 500); // Aumenta o tamanho para melhor distribuição
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        URL iconURL = getClass().getResource("/icon1.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        } else {
            System.err.println("Ícone não encontrado! Certifique-se de que 'icon1.png' está na pasta correta.");
        }

        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Painel de informações do aventureiro
        JPanel painelInfo = new JPanel(new GridLayout(3, 1));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações do aventureiro"));
        painelInfo.add(new JLabel("Nome: " + aventureiro.getNome()));
        painelInfo.add(new JLabel("Classe: " + aventureiro.getClasse()));
        painelInfo.add(new JLabel("Nível: " + aventureiro.getNivel()));

        // Ajusta tamanho do painel de informações
        painelInfo.setPreferredSize(new Dimension(400, 80));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(painelInfo, gbc);

        // Lista de missões disponíveis
        listaMissoesModel = new DefaultListModel<>();
        carregarMissoes();
        listaMissoes = new JList<>(listaMissoesModel);
        listaMissoes.setFixedCellHeight(25); // Aumenta altura das células para evitar corte
        listaMissoes.setVisibleRowCount(5); // Garante mais espaço para visualização
        listaMissoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaMissoes);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Permite expansão vertical
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Botão para escolher missão
        JButton btnEscolherMissao = new JButton("Escolher Missão");
        btnEscolherMissao.addActionListener((ActionEvent e) -> escolherMissao());

        // Botão para voltar à tela de login
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener((ActionEvent e) -> voltarParaLogin());

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        painelBotoes.add(btnEscolherMissao);
        painelBotoes.add(btnVoltar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0; // Não precisa crescer
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(painelBotoes, gbc);
    }

    private void carregarMissoes() {
        List<Missao> missoes = missaoDAO.carregar();
        listaMissoesModel.clear();
        for (Missao m : missoes) {
            listaMissoesModel.addElement(m.getTitulo() + " - Nível Requerido: " + m.getNivelRequerido());
        }
    }

    private void escolherMissao() {
        int index = listaMissoes.getSelectedIndex();
        if (index != -1) {
            List<Missao> missoes = missaoDAO.carregar();
            Missao missaoEscolhida = missoes.get(index);

            if (aventureiro.getNivel() >= missaoEscolhida.getNivelRequerido()) {
                JOptionPane.showMessageDialog(this, aventureiro.getNome() + " escolheu a missão:\n" + missaoEscolhida.getTitulo() + "\n" + "Descrição:\n" + missaoEscolhida.getDescricao());
            } else {
                JOptionPane.showMessageDialog(this, "Nível insuficiente para escolher esta missão.\nNível requerido: " + missaoEscolhida.getNivelRequerido());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma missão antes de continuar.");
        }
    }

    private void voltarParaLogin() {
        this.dispose();
        new LoginView().setVisible(true);
    }
}