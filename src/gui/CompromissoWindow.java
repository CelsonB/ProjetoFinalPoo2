package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Agenda;
import entities.Compromisso;
import service.CompromissoService;

import javax.swing.JScrollPane;

public class CompromissoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private CompromissoService compromissoService;
	private Agenda agenda; 
	
	public CompromissoWindow(Agenda agendaSelecionada) {
		this.agenda = agendaSelecionada; 
		compromissoService = new CompromissoService();
		initComponents();
		buscarCompromissos();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdicionarCompromisso = new JButton("Adicionar compromisso");
		btnAdicionarCompromisso.setBounds(10, 196, 164, 23);
		contentPane.add(btnAdicionarCompromisso);
		
		JButton btnApagarCompromisso = new JButton("Apagar compromisso");
		btnApagarCompromisso.setBounds(10, 230, 164, 23);
		contentPane.add(btnApagarCompromisso);
		
		JButton btnAtualizarCompromisso = new JButton("Atualizar compromisso");
		btnAtualizarCompromisso.setBounds(216, 196, 164, 23);
		contentPane.add(btnAtualizarCompromisso);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(216, 230, 164, 23);
		contentPane.add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 370, 174);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id","nome","descricao"
			}
		));
	}
	
	public void buscarCompromissos() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		
		modelo.fireTableDataChanged();
		
		modelo.setRowCount(0);
		
		
		List<Compromisso> compromissoLista;
		try {
			compromissoLista = this.compromissoService.listaCompromisso(this.agenda.getId());
			
			for(Compromisso compromisso : compromissoLista) {
				modelo.addRow(new Object[] {
						compromisso.getId(),
						compromisso.getTitulo(),
						compromisso.getDescricao(),
						
				});
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
}
