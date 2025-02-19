package VendinhaDeD.view;

import VendinhaDeD.controller.AdministradorController;
import VendinhaDeD.model.Missao;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerenciarMissoesView extends JFrame {
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> model;
    private JList<String> lstMissoes;
    private JButton btnAdicionar, btnEditar, btnExcluir;
    private AdministradorController adminController;

    public GerenciarMissoesView() {
        adminController = new AdministradorController();

        setTitle("Gerenciar Missões");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout()); // Definindo GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.BOTH; // Expandir os componentes horizontalmente

        model = new DefaultListModel<>();
        lstMissoes = new JList<>(model);
        carregarMissoes();

        JScrollPane scrollPane = new JScrollPane(lstMissoes);
        
        // Configuração do painel de missões (ocupa toda a largura)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Ocupa três colunas
        gbc.weightx = 1;
        gbc.weighty = 1; // Permite que a lista cresça
        add(scrollPane, gbc);

        // Criando painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centraliza os botões
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);

        // Adicionando o painel de botões na parte inferior
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3; // Ocupa três colunas
        gbc.weighty = 0; // Não cresce
        add(panelBotoes, gbc);

        // Eventos dos botões
        btnAdicionar.addActionListener(e -> adicionarMissao());
        btnEditar.addActionListener(e -> editarMissao());
        btnExcluir.addActionListener(e -> excluirMissao());

        setLocationRelativeTo(null);
    }

    private void carregarMissoes() {
        model.clear();
        List<Missao> missoes = adminController.listarMissoes(); // Usa o AdministradorController
        for (Missao m : missoes) {
            model.addElement(m.getTitulo() + " - Nível: " + m.getNivelRequerido());
        }
    }

    private void adicionarMissao() {
        String titulo = JOptionPane.showInputDialog("Título da Missão:");
        String descricao = JOptionPane.showInputDialog("Descrição:");
        int nivelMinimo = Integer.parseInt(JOptionPane.showInputDialog("Nível mínimo:"));

        if (titulo != null && descricao != null) {
            adminController.cadastrarMissao(titulo, descricao, nivelMinimo);
            carregarMissoes();
        }
    }

    private void editarMissao() {
        int index = lstMissoes.getSelectedIndex();
        if (index >= 0) {
            List<Missao> missoes = adminController.listarMissoes();
            Missao selecionada = missoes.get(index);

            String novoTitulo = JOptionPane.showInputDialog("Novo título:", selecionada.getTitulo());
            String novaDescricao = JOptionPane.showInputDialog("Nova descrição:", selecionada.getDescricao());
            int novoNivel = Integer.parseInt(JOptionPane.showInputDialog("Novo nível mínimo:", selecionada.getNivelRequerido()));

            if (novoTitulo != null && novaDescricao != null) {
                adminController.atualizarMissao(selecionada.getTitulo(), novaDescricao, novoNivel);
                carregarMissoes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma missão para editar.");
        }
    }

    private void excluirMissao() {
        int index = lstMissoes.getSelectedIndex();
        if (index >= 0) {
            List<Missao> missoes = adminController.listarMissoes();
            Missao selecionada = missoes.get(index);
            adminController.removerMissao(selecionada.getTitulo());
            carregarMissoes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma missão para excluir.");
        }
    }
}