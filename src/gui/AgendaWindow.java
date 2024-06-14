package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPaneFilho;
	
	private JTable table;

	
	public AgendaWindow() {
		initComponents();
	}
	
	
	public void initComponents() {
		
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 318, 354);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 276, 197);
			contentPane.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Descri\u00E7\u00E3o", "Nome"
				}
			));
			
			JPanel panel = new JPanel();
			panel.setBounds(10, 221, 276, 57);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JButton btnCriarAgeda = new JButton("Criar nova agenda");
			btnCriarAgeda.setBounds(0, 0, 122, 23);
			panel.add(btnCriarAgeda);
			
			JButton btnDeletarAgenda = new JButton("Deletar agenda");
			btnDeletarAgenda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnDeletarAgenda.setBounds(0, 34, 122, 23);
			panel.add(btnDeletarAgenda);
			
			JButton btnEditarAgenda = new JButton("Editar agenda");
			btnEditarAgenda.setBounds(146, 0, 130, 23);
			panel.add(btnEditarAgenda);
			
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.setBounds(146, 34, 130, 23);
			panel.add(btnVoltar);
			btnCriarAgeda.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					cadastrarAgenda();
					
				}
			});
		
	}
	
	

	private JTextField textFieldNomeAgenda;
	
	
	private void cadastrarAgenda() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 343);
		contentPaneFilho = new JPanel();
		contentPaneFilho.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPaneFilho);
		contentPaneFilho.setLayout(null);
		
		JLabel lblNomeAgenda = new JLabel("Nome da agenda");
		lblNomeAgenda.setBounds(10, 11, 99, 14);
		contentPaneFilho.add(lblNomeAgenda);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o da agenda");
		lblDescricao.setBounds(10, 88, 137, 14);
		contentPaneFilho.add(lblDescricao);
		
		textFieldNomeAgenda = new JTextField();
		textFieldNomeAgenda.setBounds(10, 29, 201, 20);
		contentPaneFilho.add(textFieldNomeAgenda);
		textFieldNomeAgenda.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 113, 287, 135);
		contentPaneFilho.add(panel);
		panel.setLayout(null);
		
		JTextArea textAreaDescricao = new JTextArea();
		textAreaDescricao.setBounds(3, 2, 280, 130);
		panel.add(textAreaDescricao);
		textAreaDescricao.setLineWrap(true);
		
		JButton btnCriarAgenda = new JButton("Criar agenda");
		btnCriarAgenda.setBounds(10, 270, 140, 23);
		contentPaneFilho.add(btnCriarAgenda);
		
		btnCriarAgenda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				criarAgenda();
			}
		});
	
		
		JButton btnVoltarCriarAgenda = new JButton("Voltar");
		btnVoltarCriarAgenda.setBounds(157, 270, 140, 23);
		contentPaneFilho.add(btnVoltarCriarAgenda);
		
		
		btnVoltarCriarAgenda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setContentPane(contentPane);
				contentPaneFilho.setVisible(false);
				
			}
		});		
	}
	public void criarAgenda() {
		
	}
}
