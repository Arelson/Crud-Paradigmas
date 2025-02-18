package VendinhaDeD.view;
import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.dao.MissaoDAO;
import VendinhaDeD.model.Aventureiro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnSair;
    
    private AventureiroDAO aventureiroDAO;
    private MissaoDAO missaoDAO;

    public LoginView() {
        aventureiroDAO = new AventureiroDAO();
        missaoDAO = new MissaoDAO();
        
        setTitle("Login - Aventura RPG");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // Labels e campos de entrada
        add(new JLabel("Login:"));
        txtLogin = new JTextField();
        add(txtLogin);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        btnLogin = new JButton("Entrar");
        btnSair = new JButton("Sair");

        add(btnLogin);
        add(btnSair);

        // Evento do botão Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        // Evento do botão Sair
        btnSair.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    private void realizarLogin() {
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        // Verificar se é um Administrador
        if (login.equals("admin") && senha.equals("admin123")) {
            String chaveAcesso = JOptionPane.showInputDialog("Digite a chave de acesso:");
            if ("12345".equals(chaveAcesso)) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, Administrador!");
                new AdminView().setVisible(true);
                this.dispose();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Chave de acesso incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Verificar se é um Aventureiro
        List<Aventureiro> aventureiros = aventureiroDAO.carregar();
        for (Aventureiro a : aventureiros) {
            if (a.getLogin().equals(login) && a.autenticar(senha)) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + a.getNome() + "!");
                new AventureiroView(a, missaoDAO).setVisible(true);
                this.dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new LoginView().setVisible(true);
    }
}
