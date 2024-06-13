package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeUsuario;
	private JTextField textFieldSenha;
	
	
	private static CadastrarWindow cadastrar;
	
	

	
	
	public LoginWindow() {
		initComponents();
		
		this.setVisible(true);
	}
	
	public void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setBounds(10, 62, 83, 14);
		contentPane.add(labelSenha);
		
		JLabel labelNomeDeUsuario = new JLabel("Nome de usuario");
		labelNomeDeUsuario.setBounds(10, 25, 120, 14);
		contentPane.add(labelNomeDeUsuario);
		
		textFieldNomeUsuario = new JTextField();
		textFieldNomeUsuario.setBounds(115, 22, 202, 20);
		contentPane.add(textFieldNomeUsuario);
		textFieldNomeUsuario.setColumns(10);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setBounds(115, 59, 202, 20);
		contentPane.add(textFieldSenha);
		textFieldSenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				cadastrar = new CadastrarWindow();
				cadastrar.setVisible(true);
				
			
			
			}
		});
		btnCadastrar.setBounds(20, 90, 112, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(205, 90, 112, 23);
		contentPane.add(btnLogin);
	}
}
