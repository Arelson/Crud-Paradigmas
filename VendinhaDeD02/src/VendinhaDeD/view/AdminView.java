package VendinhaDeD.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AdminView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton btnGerenciarAventureiros;
    private JButton btnGerenciarMissoes;
    private JButton btnSair;
    private JButton btnVolta;

    public AdminView() {
        setTitle("Painel do Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o ícone da janela
        URL iconURL = getClass().getResource("/icon1.png");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        } else {
            System.err.println("Ícone não encontrado! Certifique-se de que 'icon.png' está na pasta correta.");
        }

        // Painel de fundo com imagem
        setContentPane(new BackgroundPanel("/background.png")); 

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Criando botões
        btnGerenciarAventureiros = new JButton("Gerenciar Aventureiros");
        btnGerenciarMissoes = new JButton("Gerenciar Missões");
        btnVolta = new JButton("Voltar");
        btnSair = new JButton("Sair");

        // Configurando botões na interface
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnGerenciarAventureiros, gbc);

        gbc.gridy = 1;
        add(btnGerenciarMissoes, gbc);

        // Criando painel para botões Voltar e Sair
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.setOpaque(false); 
        painelBotoes.add(btnVolta);
        painelBotoes.add(btnSair);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(painelBotoes, gbc);

        // Eventos dos botões
        btnGerenciarAventureiros.addActionListener(e -> new GerenciarAventureirosView().setVisible(true));
        btnGerenciarMissoes.addActionListener(e -> new GerenciarMissoesView().setVisible(true));
        btnVolta.addActionListener(e -> voltarParaLogin());
        btnSair.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    private void voltarParaLogin() {
        this.dispose();
        new LoginView().setVisible(true);
    }

    // Classe interna para criar um painel de fundo com imagem
    static class BackgroundPanel extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                backgroundImage = new ImageIcon(imageUrl).getImage();
            } else {
                System.err.println("Imagem de fundo não encontrada: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        new AdminView().setVisible(true);
    }
}