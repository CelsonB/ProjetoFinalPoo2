package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Agenda;
import entities.Compromisso;
import entities.Convite;
import entities.Usuario;
import service.AgendaService;
import service.ConviteService;
import service.UsuarioService;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConvitesWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	private JTable table;
	private UsuarioService usuarioService ;
	private JButton btnEnviarConvite;
	private JButton btnVoltar;
	private Compromisso compromisso;
	private Usuario sessao;
	private JButton btnRecusarConvite;
	
	private ConviteService conviteService;
	private List<Convite> ConviteLista;
	private Agenda agendaConvite;
	
	public ConvitesWindow(Usuario sessao, Compromisso compromisso) {
		this.compromisso = compromisso;
		this.sessao = sessao;
		 usuarioService = new UsuarioService();
		
		 initComponentsEnviarConvites();
		
		 buscarUsuarios();
	}
	
	
	public ConvitesWindow(Usuario sessao) {
		this.compromisso = compromisso;
		this.sessao = sessao;
		conviteService = new ConviteService();
		try {
			agendaConvite = buscarAgendaConvite(sessao);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponentsAceitarConvites();
		
		buscarConvites();
	}
	
	
	
	public void initComponentsEnviarConvites() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"id", "Nome", "Email"
			}
		));
		
		btnEnviarConvite = new JButton("Enviar convite");
		btnEnviarConvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					enviarConvite();
				}
				else {
					JOptionPane.showMessageDialog(null, "nenhum usuario selecionado","Enviar convites", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEnviarConvite.setBounds(10, 182, 150, 23);
		contentPane.add(btnEnviarConvite);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PerfilWindow(sessao).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(294, 182, 130, 23);
		contentPane.add(btnVoltar);

		
		
	}
	
	
	
	
	
	
	public void initComponentsAceitarConvites() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 303, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			
				"id", "compromisso","status"
				
			}
		));
		
		btnEnviarConvite = new JButton("Aceitar Convite");
		btnEnviarConvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					enviarEscolhaConvite("aceito");
				}
				else {
					JOptionPane.showMessageDialog(null, "nenhum usuario selecionado","Enviar convites", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEnviarConvite.setBounds(10, 182, 150, 23);
		contentPane.add(btnEnviarConvite);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PerfilWindow(sessao).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(10, 216, 150, 23);
		contentPane.add(btnVoltar);
		
		btnRecusarConvite = new JButton("Recusar Convite");
		btnRecusarConvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarEscolhaConvite("recusado");
			}
		});
		
		btnRecusarConvite.setBounds(170, 182, 150, 23);
		contentPane.add(btnRecusarConvite);

		
		
	}

	private void enviarEscolhaConvite(String op) {
		
		ConviteService conviteService = new ConviteService();
		try {
			if(op.equals("aceito")) {
				
				int idConvite = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				
				conviteService.aceitarConvite(op, idConvite, retornarCompromissoSelecionado());
			}else 
			if(op.equals("recusado")){
				
				int idConvite = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				
				conviteService.recusarConvite(op, idConvite);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	private void enviarConvite() {
		ConviteService conviteService = new ConviteService();
		
		int idUsuario = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
		System.out.println(idUsuario);
		try {
			if(	conviteService.enviarConvites(idUsuario, compromisso)) {
				JOptionPane.showMessageDialog(null, "Convite enviar com sucesso");
			}
		
			
		} catch (IOException | SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	private Compromisso retornarCompromissoSelecionado() {
		String titulo = table.getValueAt(table.getSelectedRow(), 1).toString();
		for(Convite conv: ConviteLista) {
			if(titulo.equals(conv.getCompromisso().getTitulo())) {
				conv.getCompromisso().setAgenda(agendaConvite);
				return conv.getCompromisso();
			}
	
		}
		return null;
	}
	
	
	
	
	private void buscarConvites() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		
		modelo.fireTableDataChanged();
		
		modelo.setRowCount(0);
		
		

		
		
		try {
			
			ConviteLista = this.conviteService.listarConvites(sessao);
			
			for(Convite conv: ConviteLista) {
				modelo.addRow(new Object[] {
						
						conv.getId(),
						conv.getCompromisso().getTitulo(),
						conv.getStatus()
						
				});
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		
	}
		}
	
	
	
	private void buscarUsuarios() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		System.out.println("funcionou");
		modelo.fireTableDataChanged();
		
		modelo.setRowCount(0);
		
		
		List<Usuario> UsuariosLista;
		
		
		try {
			
			UsuariosLista = this.usuarioService.listarUsuarios();
			
			for(Usuario user: UsuariosLista) {
				modelo.addRow(new Object[] {
						
						user.getId(),
						user.getNomeCompleto(),
						user.getEmail()
						
				});
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private Agenda buscarAgendaConvite(Usuario sessao) throws IOException, SQLException {
		AgendaService agendaService = new AgendaService();
		return agendaService.buscarAgendaConvite(sessao);
	}
}
