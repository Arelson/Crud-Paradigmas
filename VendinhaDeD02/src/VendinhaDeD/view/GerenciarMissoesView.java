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

    public GerenciarMissoesView(AdministradorController adminController) {
        this.adminController = adminController;

        setTitle("Gerenciar Miss�es");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        lstMissoes = new JList<>(model);
        carregarMissoes();

        JScrollPane scrollPane = new JScrollPane(lstMissoes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);
        add(panelBotoes, BorderLayout.SOUTH);

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
                adminController.atualizarMissao(selecionada.getTitulo(), novaDescricao, novoNivel); // Usa o AdministradorController
                carregarMissoes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma miss�o para editar.");
        }
    }

    private void excluirMissao() {
        int index = lstMissoes.getSelectedIndex();
        if (index >= 0) {
            List<Missao> missoes = adminController.listarMissoes(); // Usa o AdministradorController
            Missao selecionada = missoes.get(index);
            adminController.removerMissao(selecionada.getTitulo()); // Usa o AdministradorController
            carregarMissoes();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma miss�o para excluir.");
        }
    }
}