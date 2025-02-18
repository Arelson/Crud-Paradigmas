package VendinhaDeD.view;

import javax.swing.*;
import VendinhaDeD.controller.AdministradorController;
import VendinhaDeD.model.Aventureiro;
import java.awt.*;
import java.util.List;

public class GerenciarAventureirosView extends JFrame {
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> model;
    private JList<String> lstavent;
    private JButton bntAdicionar, bntEditar, bntExcluir;
    private AdministradorController admCrt;

    public GerenciarAventureirosView() {
        admCrt = new AdministradorController();

        setTitle("Gerenciar Aventureiros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        lstavent = new JList<>(model);
        carregarAvent();

        JScrollPane scrollPane = new JScrollPane(lstavent);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBnt = new JPanel();
        bntAdicionar = new JButton("Adicionar");
        bntEditar = new JButton("Editar");
        bntExcluir = new JButton("Excluir");

        panelBnt.add(bntAdicionar);
        panelBnt.add(bntEditar);
        panelBnt.add(bntExcluir);
        add(panelBnt, BorderLayout.SOUTH);

        bntAdicionar.addActionListener(e -> addAvent());
        bntEditar.addActionListener(e -> editAvent());
        bntExcluir.addActionListener(e -> excluirAvent());

        setLocationRelativeTo(null);
    }

    private void carregarAvent() {
        model.clear();
        List<Aventureiro> aventureiros = admCrt.listarAventureiros(); // Usa o AdministradorController
        for (Aventureiro a : aventureiros) {
            model.addElement(a.getNome() + " - " + a.getClasse() + " (Nível " + a.getNivel() + ")");
        }
    }

    private void addAvent() {
        String nome = JOptionPane.showInputDialog("Nome do aventureiro: ");
        String login = JOptionPane.showInputDialog("Login: ");
        String senha = JOptionPane.showInputDialog("Senha: ");
        String nivelStr = JOptionPane.showInputDialog("Nível atual: ");
        String[] opClasse = {"Mago", "Guerreiro", "Patrulheiro"};
        String classe = (String) JOptionPane.showInputDialog(null, "Escolha a Classe: ", "Classe",
                JOptionPane.QUESTION_MESSAGE, null, opClasse, opClasse[0]);

        int nivel;
        try {
            nivel = Integer.parseInt(nivelStr);
            if (nivel < 1) {
                JOptionPane.showMessageDialog(null, "O nível não pode ser menor que 1.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número válido para o nível.");
            return;
        }

        if (nome != null && login != null && senha != null && classe != null) {
            admCrt.cadastrarAventureiro(nome, login, senha, nivel, classe); // Usa o AdministradorController
            carregarAvent();
        } else {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
        }
    }

    private void editAvent() {
        int index = lstavent.getSelectedIndex();
        if (index >= 0) {
            List<Aventureiro> aventureiros = admCrt.listarAventureiros(); // Usa o AdministradorController
            Aventureiro selecionado = aventureiros.get(index);

            String nome = JOptionPane.showInputDialog("Novo nome: ", selecionado.getNome());
            String login = JOptionPane.showInputDialog("Novo login: ", selecionado.getLogin());
            String senha = JOptionPane.showInputDialog("Nova senha: ", selecionado.getSenha());
            String nivelStr = JOptionPane.showInputDialog("Novo nível: ", selecionado.getNivel());

            int nivel;
            try {
                nivel = Integer.parseInt(nivelStr);
                if (nivel < 1) {
                    JOptionPane.showMessageDialog(null, "O nível não pode ser menor que 1.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um número válido para o nível.");
                return;
            }

            if (nome != null && login != null && senha != null) {
                admCrt.atualizarAventureiro(nome,selecionado.getLogin(), senha, nivel); // Usa o AdministradorController
                carregarAvent();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um aventureiro para editar.");
        }
    }

    private void excluirAvent() {
        int index = lstavent.getSelectedIndex();
        if (index >= 0) {
            List<Aventureiro> aventureiros = admCrt.listarAventureiros(); // Usa o AdministradorController
            Aventureiro selecionado = aventureiros.get(index);
            admCrt.removerAventureiro(selecionado.getLogin()); // Usa o AdministradorController
            carregarAvent();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um aventureiro para excluir.");
        }
    }
}