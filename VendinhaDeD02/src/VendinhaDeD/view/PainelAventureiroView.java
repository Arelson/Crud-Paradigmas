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
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informa��es do Aventureiro"));
        painelInfo.add(new JLabel("Nome: " + aventureiro.getNome()));
        painelInfo.add(new JLabel("Classe: " + aventureiro.getClasse()));
        painelInfo.add(new JLabel("N�vel: " + aventureiro.getNivel()));

        // Lista de miss�es dispon�veis
        listaMissoesModel = new DefaultListModel<>();
        carregarMissoes();
        listaMissoes = new JList<>(listaMissoesModel);
        JScrollPane scrollPane = new JScrollPane(listaMissoes);
        
        // Bot�o para escolher miss�o
        JButton btnEscolherMissao = new JButton("Escolher Miss�o");
        btnEscolherMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherMissao();
            }
        });

        // Adicionando componentes � tela
        add(painelInfo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnEscolherMissao, BorderLayout.SOUTH);
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

            // Verifica se o n�vel do aventureiro � suficiente para a miss�o
            if (aventureiro.getNivel() >= missaoEscolhida.getNivelRequerido()) {
                JOptionPane.showMessageDialog(this, aventureiro.getNome() + " escolheu a miss�o:\n" + missaoEscolhida.getTitulo());
            } else {
                JOptionPane.showMessageDialog(this, "N�vel insuficiente para escolher esta miss�o.\nN�vel requerido: " + missaoEscolhida.getNivelRequerido());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma miss�o antes de continuar.");
        }
    }
}