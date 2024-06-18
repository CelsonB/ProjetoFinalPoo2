package gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entities.Agenda;
import entities.Compromisso;
import service.CompromissoService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;



public class CadastrarCompromissoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldDescricao;
	private JFormattedTextField textFieldHoraInicio;
	private JFormattedTextField textFieldHoraTermino;
	private JTextField textFieldLocal;
	private JFormattedTextField textFieldHoraNotificacao;

	private JFormattedTextField textFieldDataTermino;
	private JFormattedTextField textFieldDataInicio;
	private JFormattedTextField textFieldDataNotificacao;
	
	private MaskFormatter mascaraHora;
	private MaskFormatter mascaraData;
	private Agenda agenda;
	public CadastrarCompromissoWindow(Agenda agenda) {
	
		this.agenda = agenda;
		try {
			mascaraData =  new MaskFormatter("##/##/####");
			
			mascaraHora = new MaskFormatter("##:##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initCompenents();
	
	}
	
	public void initCompenents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
	getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(33, 25, 46, 14);
		getContentPane().add(lblTitulo);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setBounds(33, 64, 46, 14);
		getContentPane().add(lblDescricao);
		
		JLabel lblHoraInicio = new JLabel("Hora de inicio");
		lblHoraInicio.setBounds(33, 103, 84, 14);
		getContentPane().add(lblHoraInicio);
		
		JLabel lblHoraTermino = new JLabel("Horario de termino");
		lblHoraTermino.setBounds(33, 142, 105, 14);
		getContentPane().add(lblHoraTermino);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(33, 181, 46, 14);
		getContentPane().add(lblLocal);
		
		JLabel lblHoraNotificacao = new JLabel("Horario de notifica\u00E7\u00E3o");
		lblHoraNotificacao.setBounds(33, 220, 105, 14);
		getContentPane().add(lblHoraNotificacao);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(172, 22, 86, 20);
		getContentPane().add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(172, 61, 86, 20);
		getContentPane().add(textFieldDescricao);
		textFieldDescricao.setColumns(10);
		
		textFieldHoraInicio = new JFormattedTextField(mascaraHora);
		textFieldHoraInicio.setBounds(270, 100, 86, 20);
		getContentPane().add(textFieldHoraInicio);
		textFieldHoraInicio.setColumns(10);
		
		textFieldHoraTermino = new JFormattedTextField(mascaraHora);
		textFieldHoraTermino.setBounds(270, 139, 86, 20);
		getContentPane().add(textFieldHoraTermino);
		textFieldHoraTermino.setColumns(10);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setBounds(172, 178, 86, 20);
		getContentPane().add(textFieldLocal);
		textFieldLocal.setColumns(10);
		
		textFieldHoraNotificacao = new JFormattedTextField(mascaraHora);
		textFieldHoraNotificacao.setBounds(270, 217, 86, 20);
		getContentPane().add(textFieldHoraNotificacao);
		textFieldHoraNotificacao.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					realizarCadastro();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,ex.getMessage(),"Cadastrar compromissos",  JOptionPane.WARNING_MESSAGE );
				}
			}
		});
		btnCadastrar.setBounds(28, 261, 89, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PerfilWindow(agenda.getUsuario()).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(172, 261, 89, 23);
		contentPane.add(btnVoltar);
		
		 textFieldDataTermino = new JFormattedTextField(mascaraData);
		textFieldDataTermino.setColumns(10);
		textFieldDataTermino.setBounds(172, 139, 86, 20);
		contentPane.add(textFieldDataTermino);
		
		 textFieldDataInicio = new JFormattedTextField(mascaraData);
		textFieldDataInicio.setColumns(10);
		textFieldDataInicio.setBounds(172, 100, 86, 20);
		contentPane.add(textFieldDataInicio);
		
		 textFieldDataNotificacao = new JFormattedTextField(mascaraData);
		textFieldDataNotificacao.setColumns(10);
		textFieldDataNotificacao.setBounds(172, 217, 86, 20);
		contentPane.add(textFieldDataNotificacao);
		
	}
	
	public void realizarCadastro() throws ParseException, SQLException, IOException {
		SimpleDateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Date  date = new Date();
		
		Compromisso comp = new Compromisso();
		
		comp.setAgenda(this.agenda);
		
		String dataString;
		
		dataString =textFieldDataInicio.getText().concat(" " + textFieldHoraInicio.getText());
		
		date = new java.sql.Timestamp(formatter.parse(dataString).getTime() );
		comp.setDataHoraInicio(date);

		dataString =textFieldDataTermino.getText().concat(" " + textFieldHoraTermino.getText());
		date = new java.sql.Timestamp(formatter.parse(dataString).getTime());
		comp.setDataHoraTermino(date);

		
		
		dataString =textFieldDataNotificacao.getText().concat(" " + textFieldHoraNotificacao.getText());
		
		date = new java.sql.Timestamp(formatter.parse(dataString).getTime());
		comp.setDataHoraNotificacao(date);
		
		comp.setTitulo(textFieldTitulo.getText());
		comp.setDescricao(textFieldDescricao.getText());
		comp.setLocal(textFieldLocal.getText());
		
		CompromissoService compromissoService = new CompromissoService();
		
		compromissoService.cadastrarCompromisso(comp);
		
	}
}
