package gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Agenda;
import entities.Compromisso;
import entities.Usuario;
import service.AgendaService;
import service.CompromissoService;
import service.NotificacaoService;
import service.UsuarioService;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class PerfilWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private UsuarioService usuarioService;
	private AgendaService agendaService;
	private Usuario sessao; 
	private JTable table;
	private JTable tableAgenda;
	private CompromissoService compromissoService;
	
	
	
    private JButton uploadButton;
    
    private File selectedFile;
    
	public PerfilWindow(Usuario sessao){
		NotificacaoService notificacaoService = new NotificacaoService(sessao);
		
		
		  Thread serviceThread = new Thread(notificacaoService);
		  serviceThread.start();
		  
	
		usuarioService = new UsuarioService();
		compromissoService = new CompromissoService();
		try {
			if(sessao.getEmail()==null) this.sessao = usuarioService.visualizarUsuario(sessao.getId());
			else this.sessao = sessao;
				
			agendaService = new AgendaService();
			initComponents();
			buscarAgendas();	
			//buscarCompromissos();
				
			
		} catch (IOException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 10, 336, 144);
		contentPane.add(panel);
		
		JLabel lblNomeCompleto = new JLabel("Nome: " + this.sessao.getNomeCompleto());
		lblNomeCompleto.setBounds(10, 17, 173, 14);
		
		JLabel lblEmail = new JLabel("Email: " +this.sessao.getEmail());
		lblEmail.setBounds(10, 79, 173, 14);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento: " +this.sessao.getDataNascimento().toString());
		lblDataDeNascimento.setBounds(10, 110, 173, 14);
		
		JLabel lblGenero = new JLabel("Genero: " + retornarGenero());
		lblGenero.setBounds(10, 48, 173, 14);
		panel.setLayout(null);
		panel.add(lblNomeCompleto);
		panel.add(lblEmail);
		panel.add(lblDataDeNascimento);
		panel.add(lblGenero);
		
		JLabel lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setIcon(new ImageIcon("C:\\Users\\Acer\\Desktop\\cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTAxL3JtNjA5LXNvbGlkaWNvbi13LTAwMi1wLnBuZw.png"));
		lblFotoPerfil.setBounds(193, 17, 133, 107);
		panel.add(lblFotoPerfil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(356, 10, 119, 144);
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
		
		JButton btnAtualizarImagem = new JButton("AtualizarImagem");
		btnAtualizarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					JFileChooser fileChooser = new JFileChooser();
	                int returnValue = fileChooser.showOpenDialog(null);
	                if (returnValue == JFileChooser.APPROVE_OPTION) {
	                    selectedFile = fileChooser.getSelectedFile();
	                    usuarioService.AtualizarImagem(selectedFile, sessao);
	                    lblFotoPerfil.setIcon(new ImageIcon(usuarioService.pegarImagem(sessao)));
	                }
	                
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Atualizar foto de perfil", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		btnAtualizarImagem.setBounds(0, 101, 119, 23);
		panel_1.add(btnAtualizarImagem);
		
		JButton btnCompromissos = new JButton("Configurar compromissos");
		btnCompromissos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurarCompromissos();
			}
		});
		btnCompromissos.setBounds(10, 374, 194, 23);
		contentPane.add(btnCompromissos);
		
		JButton btnAgenda = new JButton("Configurar agendas");
		
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAgenda();
			}
		});
		btnAgenda.setBounds(279, 374, 194, 23);
		contentPane.add(btnAgenda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 165, 194, 198);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id","Titulo", "Data de inicio"
			}
		));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			
		});
		scrollPane_1.setBounds(281, 166, 194, 197);
		contentPane.add(scrollPane_1);
		
		tableAgenda = new JTable();
		tableAgenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarCompromissos();
			}
		});
		scrollPane_1.setViewportView(tableAgenda);
		tableAgenda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Agenda"
			}
		));
		
		JButton btnEnviarConvites = new JButton("Enviar convites");
		btnEnviarConvites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(table.getSelectedRow()!=-1) {
					new ConvitesWindow(sessao,retornarCompromisso()).setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Nenhum compromisso selecionado", "Convites", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEnviarConvites.setBounds(10, 408, 194, 23);
		contentPane.add(btnEnviarConvites);
		
		JButton btnVerConvites = new JButton("Ver convites");
		btnVerConvites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					new ConvitesWindow(sessao).setVisible(true);
					dispose();
			
				
			
			}
		});
		btnVerConvites.setBounds(279, 408, 196, 23);
		contentPane.add(btnVerConvites);
		
		
	}
	
	private void abrirAgenda() {
		new AgendaWindow(sessao).setVisible(true);
		dispose();
	}
	
	private String retornarGenero() {
				if(sessao.getGenero().equals(Usuario.Genero.F)) {
					return "Feminino";
				}else {
					return "Masculino";
				}
	}
	
	public void buscarAgendas() {
		
		DefaultTableModel modelo = (DefaultTableModel) tableAgenda.getModel();
		
		modelo.fireTableDataChanged();
		
		modelo.setRowCount(0);
		
		
		List<Agenda> agendaLista;
		try {
			agendaLista = this.agendaService.listaAgendas(sessao.getId());
			
			for(Agenda agenda : agendaLista) {
				agenda.setUsuario(this.sessao);
				
				modelo.addRow(new Object[] {
						agenda.getId(),
						agenda.getNome(),
						
						
				});
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
		 
		
	}
	
	public void buscarCompromissos() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		System.out.println("funcionou");
		modelo.fireTableDataChanged();
		
		modelo.setRowCount(0);
		
		
		List<Compromisso> compromissoLista;
		try {
			int id = Integer.parseInt(tableAgenda.getValueAt(tableAgenda.getSelectedRow(), 0).toString());
			compromissoLista = this.compromissoService.listaCompromisso(id);
			
			for(Compromisso compromisso : compromissoLista) {
				modelo.addRow(new Object[] {
						
						compromisso.getId(),
						compromisso.getTitulo(),
						compromisso.getDataHoraInicio(),
						
						
				});
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	
	private Compromisso retornarCompromisso() {
		List<Compromisso> agendaCompromisso;
		try {
			
			if(table.getSelectedRow()==-1)return null;
			int idCompromissoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
			agendaCompromisso = this.compromissoService.listaCompromisso(retornarAgendaRow().getId());
			
			for(Compromisso comp : agendaCompromisso) {
			if(comp.getId()==idCompromissoSelecionado) {
			
				return comp;
			}
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
		
	}
	
	
	private void	configurarCompromissos() {
		
	
		
		Agenda agendaSelecionada = retornarAgendaRow();
		
		if(agendaSelecionada !=null) {
			new CompromissoWindow(agendaSelecionada, sessao).setVisible(true);
			dispose();
			
		}else {
			JOptionPane.showMessageDialog(null,"Nenhuma agenda selecionada","Editar compromissos",  JOptionPane.WARNING_MESSAGE );
		}
		
	
	}
	
	
	private Agenda retornarAgendaRow() {
		List<Agenda> agendaLista;
		try {
			
			if(tableAgenda.getSelectedRow()==-1)return null;
			String nomeAgendaSelecionada = tableAgenda.getValueAt(tableAgenda.getSelectedRow(), 1).toString();
			agendaLista = this.agendaService.listaAgendas(sessao.getId());
			
			for(Agenda agenda : agendaLista) {
			if(agenda.getNome().equals(nomeAgendaSelecionada)) {
				agenda.setUsuario(this.sessao);
				return agenda;
			}
			}
			
		} catch (IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
		
	}
	
	
}
