package gui;

import java.awt.EventQueue;

import entities.Usuario;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Usuario user = new Usuario();
					user.setId(3);
					AgendaWindow login = new AgendaWindow(user);
					login.setVisible(true);
					
//					CadastrarWindow cadastrar = new CadastrarWindow();
//					cadastrar.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
