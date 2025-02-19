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

        setTitle("Gerenciar Miss�es");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout()); // Definindo GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espa�amento entre os componentes
        gbc.fill = GridBagConstraints.BOTH; // Expandir os componentes horizontalmente

        model = new DefaultListModel<>();
        lstMissoes = new JList<>(model);
        carregarMissoes();

        JScrollPane scrollPane = new JScrollPane(lstMissoes);
        
        // Configura��o do painel de miss�es (ocupa toda a largura)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Ocupa tr�s colunas
        gbc.weightx = 1;
        gbc.weighty = 1; // Permite que a lista cres�a
        add(scrollPane, gbc);

        // Criando painel de bot�es
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centraliza os bot�es
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);

        // Adicionando o painel de bot�es na parte inferior
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3; // Ocupa tr�s colunas
        gbc.weighty = 0; // N�o cresce
        add(panelBotoes, gbc);

        // Eventos dos bot�es
        btnAdicionar.addActionListener(e -> adicionarMissao());
        btnEditar.addActionListener(e -> editarMissao());
        btnExcluir.addActionListener(e -> excluirMissao());

        setLocationRelativeTo(null);
    }

    private void carregarMissoes() {
        model.clear();
        List<Missao> missoes = adminController.listarMissoes(); // Usa o AdministradorController
        for (Missao m : missoes) {
            model.addElement(m.getTitulo() + " - N�vel: " + m.getNivelRequerido());
        }
    }

    private void adicionarMissao() {
        String titulo = JOptionPane.showInputDialog("T�tulo da Miss�o:");
        String descricao = JOptionPane.showInputDialog("Descri��o:");
        int nivelMinimo = Integer.parseInt(JOptionPane.showInputDialog("N�vel m�nimo:"));

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

            String novoTitulo = JOptionPane.showInputDialog("Novo t�tulo:", selecionada.getTitulo());
            String novaDescricao = JOptionPane.showInputDialog("Nova descri��o:", selecionada.getDescricao());
            int novoNivel = Integer.parseInt(JOptionPane.showInputDialog("Novo n�vel m�nimo:", selecionada.getNivelRequerido()));

            if (novoTitulo != null && novaDescricao != null) {
                adminController.atualizarMissao(selecionada.getTitulo(), novaDescricao, novoNivel);
                carregarMissoes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma miss�o para editar.");
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
            JOptionPane.showMessageDialog(this, "Selecione uma miss�o para excluir.");
        }
    }
}