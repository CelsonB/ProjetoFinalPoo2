package gui;

import java.awt.EventQueue;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					AgendaWindow login = new AgendaWindow();
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
