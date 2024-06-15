package gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entities.Usuario;
import service.UsuarioService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeCompleto;
	private JTextField textFieldEmail;
	private JTextField textFieldUsuario;
	private JTextField textFieldSenha;
	private JFormattedTextField textFieldDataDeNascimento;
	private JComboBox comboBoxGenero ;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CadastrarWindow frame = new CadastrarWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	private MaskFormatter mascaraData;
	

	
	
	
	
	
	public CadastrarWindow() {

		try {
			mascaraData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initCompoenents();
	}
	
	public void initCompoenents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeCompleto = new JLabel("Nome completo");
		lblNomeCompleto.setBounds(10, 14, 86, 14);
		contentPane.add(lblNomeCompleto);
		
		textFieldNomeCompleto = new JTextField();
		textFieldNomeCompleto.setBounds(147, 11, 86, 20);
		contentPane.add(textFieldNomeCompleto);
		textFieldNomeCompleto.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 46, 46, 14);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(147, 42, 86, 20);
		contentPane.add(textFieldEmail);
		
		JLabel lblNomeDeUsuario = new JLabel("Nome de usuario");
		lblNomeDeUsuario.setBounds(10, 82, 86, 14);
		contentPane.add(lblNomeDeUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBounds(147, 78, 86, 20);
		contentPane.add(textFieldUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 111, 86, 14);
		contentPane.add(lblSenha);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(147, 107, 86, 20);
		contentPane.add(textFieldSenha);
		
		JButton btnCadastro = new JButton("Realizar cadastro");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				realizarCadastro();
			}
		});
		btnCadastro.setBounds(10, 216, 125, 23);
		contentPane.add(btnCadastro);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(10, 140, 86, 14);
		contentPane.add(lblGenero);
		
		comboBoxGenero = new JComboBox();
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] {"Feminino", "Masculino"}));
		comboBoxGenero.setBounds(147, 138, 86, 22);
		contentPane.add(comboBoxGenero);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
		lblDataDeNascimento.setBounds(10, 178, 101, 14);
		contentPane.add(lblDataDeNascimento);
		
		textFieldDataDeNascimento = new JFormattedTextField(mascaraData);
		textFieldDataDeNascimento.setColumns(10);
		textFieldDataDeNascimento.setBounds(147, 174, 86, 20);
		contentPane.add(textFieldDataDeNascimento);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginWindow().setVisible(true);
				dispose();
				
			}
		});
		btnVoltar.setBounds(144, 216, 89, 23);
		contentPane.add(btnVoltar);
	}
	
	private UsuarioService usuarioService = new UsuarioService();
	public void realizarCadastro()  {
		SimpleDateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date;
		try {	
			
			
			
			
			  String dataDeNascimento = textFieldDataDeNascimento.getText();
		        if (dataDeNascimento.isEmpty() ||!formatter.format(formatter.parse(dataDeNascimento)).equals(dataDeNascimento)) {
		            JOptionPane.showMessageDialog(null, "Data de nascimento inválida");
		            return;
		        }
		        
		    Usuario userCadastro = new Usuario();
		        
			date = formatter.parse(dataDeNascimento);
			userCadastro.setDataNascimento(date);
			userCadastro.setNomeCompleto(textFieldNomeCompleto.getText());
			userCadastro.setNomeUsuario(textFieldUsuario.getText());
			userCadastro.setEmail(textFieldEmail.getText());
			
			if(comboBoxGenero.getSelectedItem().toString().equals("Feminino")) {
				userCadastro.setGenero(Usuario.Genero.F);
			}else if(comboBoxGenero.getSelectedItem().toString().equals("Masculino")) {
				userCadastro.setGenero(Usuario.Genero.M);
			}
			userCadastro.setSenha(textFieldSenha.getText());
		
			if(usuarioService.realizarCadastro(userCadastro)) {
				JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
				
				new LoginWindow().setVisible(true);
				dispose();

			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
