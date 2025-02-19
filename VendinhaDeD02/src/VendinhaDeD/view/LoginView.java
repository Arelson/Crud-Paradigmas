package VendinhaDeD.view;

import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;

public class LoginView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnSair;

    private AventureiroDAO aventureiroDAO;
    private MissaoDAO missaoDAO;
    private PainelAventureiroView PnAvent;

    public LoginView() {
        aventureiroDAO = new AventureiroDAO();
        missaoDAO = new MissaoDAO();

        // Carregar ícone corretamente
        URL iconURL = getClass().getResource("/icon1.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        } else {
            System.err.println("Ícone não encontrado! Certifique-se de que 'icon.png' está na pasta correta.");
        }

        setTitle("Login - Aventura RPG");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels e campos de entrada
        JLabel lblLogin = new JLabel("Login:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblLogin, gbc);

        txtLogin = new JTextField(15);
        gbc.gridx = 1;
        add(txtLogin, gbc);

        JLabel lblSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblSenha, gbc);

        txtSenha = new JPasswordField(15);
        gbc.gridx = 1;
        add(txtSenha, gbc);

        // Painel para botões (mantém responsividade)
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());

        btnLogin = new JButton("Entrar");
        btnSair = new JButton("Sair");

        panelBotoes.add(btnLogin);
        panelBotoes.add(btnSair);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotoes, gbc);

        // Eventos dos botões
        btnLogin.addActionListener(this::realizarLogin);
        btnSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void realizarLogin(ActionEvent e) {
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verifica se o usuário é administrador
        if (login.equals("admin") && senha.equals("admin123")) {
            String chaveAcesso = JOptionPane.showInputDialog("Digite a chave de acesso:");
            if ("12345".equals(chaveAcesso)) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, Administrador!");
                new AdminView().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Chave de acesso incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        // Verifica se o usuário é um aventureiro
        List<Aventureiro> aventureiros = aventureiroDAO.carregar();
        for (Aventureiro a : aventureiros) {
            if (a.getLogin().equals(login) && a.autenticar(senha)) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + a.getNome() + "!");
                PnAvent = new PainelAventureiroView(a);
                PnAvent.setVisible(true);
                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}