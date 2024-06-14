package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

public class CriarAgendaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneFilho;
	private JTextField textFieldNomeAgenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarAgendaWindow frame = new CriarAgendaWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CriarAgendaWindow() {
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
		
		JButton btnVoltarCriarAgenda = new JButton("Voltar");
		btnVoltarCriarAgenda.setBounds(157, 270, 140, 23);
		contentPaneFilho.add(btnVoltarCriarAgenda);
	}
}
