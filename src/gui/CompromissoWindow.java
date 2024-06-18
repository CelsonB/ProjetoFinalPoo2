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
import entities.Usuario;
import service.CompromissoService;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompromissoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private CompromissoService compromissoService;
	private Agenda agenda; 
	private Usuario sessao;
	private List<Compromisso> compromissoLista;
	public CompromissoWindow(Agenda agendaSelecionada, Usuario sessao) {
		this.agenda = agendaSelecionada; 
		this.sessao = sessao;
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
		btnAdicionarCompromisso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCompromisso();
			}
		});
		btnAdicionarCompromisso.setBounds(10, 196, 164, 23);
		contentPane.add(btnAdicionarCompromisso);
		
		JButton btnApagarCompromisso = new JButton("Apagar compromisso");
		btnApagarCompromisso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					if(table.getSelectedRow()!=-1)apagarCompromissos();
					else JOptionPane.showMessageDialog(null,"Nenhuma coluna selecionada","Apagar compromisso",  JOptionPane.WARNING_MESSAGE );	
					
					
					
					
				} catch (IOException | SQLException e1) {
				
					JOptionPane.showMessageDialog(null,e1.getMessage(),"Apagar compromisso",  JOptionPane.WARNING_MESSAGE );
				}
			}
		});
		btnApagarCompromisso.setBounds(10, 230, 164, 23);
		contentPane.add(btnApagarCompromisso);
		
		JButton btnAtualizarCompromisso = new JButton("Atualizar compromisso");
		btnAtualizarCompromisso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow()!=-1) {
					atualizarCompromisso();
				}else {
					JOptionPane.showMessageDialog(null,"Nenhuma coluna selecionada","Atualizar compromisso",  JOptionPane.WARNING_MESSAGE );	
				}
				
				
			}
		});
		btnAtualizarCompromisso.setBounds(216, 196, 164, 23);
		contentPane.add(btnAtualizarCompromisso);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PerfilWindow(sessao).setVisible(true);
			}
		});
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
	
	public void adicionarCompromisso() {
		new CadastrarCompromissoWindow(agenda).setVisible(true);
		dispose();
	}
	
	public void atualizarCompromisso() {
		
		new CadastrarCompromissoWindow(agenda, retornarCompromisso()).setVisible(true);
		dispose();
	}
	
	
	public void apagarCompromissos() throws IOException, SQLException  {
		
		
		if(JOptionPane.showConfirmDialog(null, "deseja realmente apagar esse compromisso?")==0) {
			if(compromissoService.apagarCompromisso(retornarCompromisso())) {
				JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso", "Apagar compromisso",  JOptionPane.DEFAULT_OPTION );
				buscarCompromissos();
			}
		}
			
			
			
		
	}
	
	private Compromisso retornarCompromisso() {
		
		int op = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()) ; 
	
		for(Compromisso comp : compromissoLista) {
			if(comp.getId()==op) {
				return comp;
			}
		}
		return null;
	}
}























