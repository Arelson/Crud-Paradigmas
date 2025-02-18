package VendinhaDeD.view;

import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import VendinhaDeD.model.Missao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelAventureiroView extends JFrame {
    private static final long serialVersionUID = 1L;
    private Aventureiro aventureiro;
    private MissaoDAO missaoDAO;
    private DefaultListModel<String> listaMissoesModel;
    private JList<String> listaMissoes;
    private JButton btnVoltar; // Bot�o para voltar � tela de login

    public PainelAventureiroView(Aventureiro aventureiro) {
        this.aventureiro = aventureiro;
        this.missaoDAO = new MissaoDAO();
        setTitle("Painel do Aventureiro - " + aventureiro.getNome());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel de informa��es do aventureiro
        JPanel painelInfo = new JPanel(new GridLayout(3, 1));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informacoes do Aventureiro"));
        painelInfo.add(new JLabel("Nome: " + aventureiro.getNome()));
        painelInfo.add(new JLabel("Classe: " + aventureiro.getClasse()));
        painelInfo.add(new JLabel("Nivel: " + aventureiro.getNivel()));

        // Lista de miss�es dispon�veis
        listaMissoesModel = new DefaultListModel<>();
        carregarMissoes();
        listaMissoes = new JList<>(listaMissoesModel);
        JScrollPane scrollPane = new JScrollPane(listaMissoes);

        // Bot�o para escolher miss�o
        JButton btnEscolherMissao = new JButton("Escolher Missao");
        btnEscolherMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherMissao();
            }
        });

        // Bot�o para voltar � tela de login
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaLogin();
            }
        });

        // Painel para os bot�es
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2));
        painelBotoes.add(btnEscolherMissao);
        painelBotoes.add(btnVoltar);

        // Adicionando componentes � tela
        add(painelInfo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void carregarMissoes() {
        List<Missao> missoes = missaoDAO.carregar();
        listaMissoesModel.clear();
        for (Missao m : missoes) {
            listaMissoesModel.addElement(m.getTitulo() + " - Nivel Requerido: " + m.getNivelRequerido());
        }
    }

    private void escolherMissao() {
        int index = listaMissoes.getSelectedIndex();
        if (index != -1) {
            List<Missao> missoes = missaoDAO.carregar();
            Missao missaoEscolhida = missoes.get(index);

            // Verifica se o n�vel do aventureiro � suficiente para a miss�o
            if (aventureiro.getNivel() >= missaoEscolhida.getNivelRequerido()) {
                JOptionPane.showMessageDialog(this, aventureiro.getNome() + " escolheu a missao:\n" + missaoEscolhida.getTitulo());
            } else {
                JOptionPane.showMessageDialog(this, "Nivel insuficiente para escolher esta missao.\nNivel requerido: " + missaoEscolhida.getNivelRequerido());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma missao antes de continuar.");
        }
    }

    private void voltarParaLogin() {
        // Fecha a janela atual (PainelAventureiroView)
        this.dispose();

        // Abre a tela de login (LoginView)
        new LoginView().setVisible(true);
    }
}