package VendinhaDeD.view;

import javax.swing.*;

import VendinhaDeD.controller.AdministradorController;
import VendinhaDeD.dao.AventureiroDAO;
import VendinhaDeD.model.Aventureiro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciarAventureirosView extends JFrame{
	/**
	 * 
	 */
	private DefaultListModel<String> model;
	private JList<String> lstavent;
	private JButton bntAdicionar, bntEditar, bntExcluir;
	private AventureiroDAO aventureiroDAO;
	private AdministradorController admCrt;
	
	public GerenciarAventureirosView() {
		aventureiroDAO = new AventureiroDAO();
		admCrt = new AdministradorController();
		
		setTitle("Gerenciar aventureiro");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout((new BorderLayout()));
		
		model = new DefaultListModel<>();
		lstavent = new JList<>();
		carregarAvent();
		
		JScrollPane scrollPane = new JScrollPane(lstavent);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelBnt = new JPanel();
		bntAdicionar = new JButton("AddAvent");
		bntEditar = new JButton("EditAvent");
		bntExcluir = new JButton("ExcluirAvent");
		
		panelBnt.add(bntAdicionar);
		panelBnt.add(bntEditar);
		panelBnt.add(bntExcluir);
		add(panelBnt, BorderLayout.SOUTH);
		
		bntAdicionar.addActionListener(e -> addAvent());
		bntEditar.addActionListener(e -> editAvent());
		bntExcluir.addActionListener(e -> excluirAvent());
		
	}
	
	private void carregarAvent() {
		model.clear();
		List<Aventureiro> aventureiros = aventureiroDAO.carregar();
		for(Aventureiro a : aventureiros) {
			model.addElement(a.getNome() + " " + a.getClasse() + " ("+ a.getNivel()+")");
		}
	}
	
	private void addAvent() {
		String nome = JOptionPane.showInputDialog("Nome do aventureiro: ");
		String login = JOptionPane.showInputDialog("Login: ");
		String senha = JOptionPane.showInputDialog("Sneha: ");
		String nivelStr = JOptionPane.showInputDialog("Nivel atual: ");
		
		String[] opClasse = {"Mago", "Guerreiro", "Patrulheiro"};
		
		String classe = (String) JOptionPane.showInputDialog(null, "Escolha a Classe: ", "Classe", 
				JOptionPane.QUESTION_MESSAGE, null, opClasse, opClasse[0]);
		
		int nivel;
		try {
			nivel = Integer.parseInt(nivelStr);
			if(nivel < 1) {
				JOptionPane.showMessageDialog(null, "O nivel não pode ser zero");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite um numero valido");
			return;
		}
		
		if(nome != null && login != null && senha != null && nivel != 0) {
			admCrt.cadastrarAventureiro(nome, login, senha, nivel, classe);
		}else {
			JOptionPane.showMessageDialog(this, "Todos os campos são obrigatorios");
		}
		
	}
	
	private void editAvent() {
		int index = lstavent.getSelectedIndex();
		if(index >= 0) {
			List<Aventureiro> aventureiros = aventureiroDAO.carregar();
			Aventureiro slect = aventureiros.get(index);
			
			String nome = JOptionPane.showInputDialog("Novo nome: ");
			String login = JOptionPane.showInputDialog("Novo Login: ");
			String senha = JOptionPane.showInputDialog("Nova Sneha: ");
			String nivelStr = JOptionPane.showInputDialog("Novo nivel");
			
			int nivel;
			try {
				nivel = Integer.parseInt(nivelStr);
				if(nivel < 1) {
					JOptionPane.showMessageDialog(null, "O nivel não pode ser zero");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Digite um numero valido");
				return;
			}
			
			if(nome != null && login != null && senha != null && nivel != 0) {
				slect.setNome(nome);
				slect.setLogin(login);
				slect.setSenha(senha);
				slect.setNivel(nivel);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Selecione um aventureiro");
		}
	}
	
	private void excluirAvent() {
		int index = lstavent.getSelectedIndex();
		if(index >= 0) {
			List<Aventureiro> aventureiros = aventureiroDAO.carregar();
			aventureiros.remove(index);
			aventureiroDAO.atualizar(aventureiros);
			carregarAvent();
			
		}else {
			JOptionPane.showMessageDialog(this, "Selecione um aventureiro");
		}
			
	}
	
	private static final long serialVersionUID = 1L;

}
