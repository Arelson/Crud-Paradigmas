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

        // Painel de informações do aventureiro
        JPanel painelInfo = new JPanel(new GridLayout(3, 1));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações do Aventureiro"));
        painelInfo.add(new JLabel("Nome: " + aventureiro.getNome()));
        painelInfo.add(new JLabel("Classe: " + aventureiro.getClasse()));
        painelInfo.add(new JLabel("Nível: " + aventureiro.getNivel()));

        // Lista de missões disponíveis
        listaMissoesModel = new DefaultListModel<>();
        carregarMissoes();
        listaMissoes = new JList<>(listaMissoesModel);
        JScrollPane scrollPane = new JScrollPane(listaMissoes);
        
        // Botão para escolher missão
        JButton btnEscolherMissao = new JButton("Escolher Missão");
        btnEscolherMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolherMissao();
            }
        });

        // Adicionando componentes à tela
        add(painelInfo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnEscolherMissao, BorderLayout.SOUTH);
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

            // Verifica se o nível do aventureiro é suficiente para a missão
            if (aventureiro.getNivel() >= missaoEscolhida.getNivelRequerido()) {
                JOptionPane.showMessageDialog(this, aventureiro.getNome() + " escolheu a missão:\n" + missaoEscolhida.getTitulo());
            } else {
                JOptionPane.showMessageDialog(this, "Nível insuficiente para escolher esta missão.\nNível requerido: " + missaoEscolhida.getNivelRequerido());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma missão antes de continuar.");
        }
    }
}