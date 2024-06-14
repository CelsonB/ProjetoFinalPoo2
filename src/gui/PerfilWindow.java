package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Usuario;
import service.UsuarioService;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerfilWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private UsuarioService usuarioService;

	private Usuario sessao; 
	public PerfilWindow(Usuario user){
		
		usuarioService = new UsuarioService();
		try {
			sessao = usuarioService.visualizarUsuario(user);
			
			System.out.println(sessao.toString());
		} catch (IOException | SQLException e) {
			System.out.println(e.getMessage());
		}
		initComponents();
		
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 10, 285, 99);
		contentPane.add(panel);
		
		JLabel lblNomeCompleto = new JLabel("Nome: " + sessao.getNomeCompleto());
		lblNomeCompleto.setBounds(10, 8, 265, 14);
		
		JLabel lblEmail = new JLabel("Email: " +sessao.getEmail());
		lblEmail.setBounds(10, 52, 265, 14);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento: " +sessao.getDataNascimento().toString());
		lblDataDeNascimento.setBounds(10, 74, 265, 14);
		
		JLabel lblGenero = new JLabel("Genero: " +sessao.getGenero().toString());
		lblGenero.setBounds(10, 30, 265, 14);
		panel.setLayout(null);
		panel.add(lblNomeCompleto);
		panel.add(lblEmail);
		panel.add(lblDataDeNascimento);
		panel.add(lblGenero);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(305, 10, 119, 99);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAtualizarPerfil = new JButton("Atualizar perfil");
		btnAtualizarPerfil.setBounds(0, 7, 119, 23);
		panel_1.add(btnAtualizarPerfil);
		
		JButton btnDeletarPerfil = new JButton("Deletar perfil");
		btnDeletarPerfil.setBounds(0, 37, 119, 23);
		panel_1.add(btnDeletarPerfil);
		
		JButton btnDeslogar = new JButton("Deslogar");
		btnDeslogar.setBounds(0, 67, 119, 23);
		panel_1.add(btnDeslogar);
		
		JButton btnCompromissos = new JButton("Compromissos");
		btnCompromissos.setBounds(10, 227, 87, 23);
		contentPane.add(btnCompromissos);
		
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAgenda();
			}
		});
		btnAgenda.setBounds(335, 227, 89, 23);
		contentPane.add(btnAgenda);
		
		
	}
	
	private void abrirAgenda() {
		new AgendaWindow(sessao);
	}
	
	private String retornarGenero() {
				if(sessao.getGenero().equals(Usuario.Genero.F)) {
					return "Feminino";
				}else {
					return "Masculino";
				}
	}
}
