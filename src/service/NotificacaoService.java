package service;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.swing.JOptionPane;

import dao.CompromissoDao;
import entities.Compromisso;
import entities.Usuario;

public class NotificacaoService  implements Runnable{

	
	
	 private Timer timer;
	 private Usuario sessao;
	 private CompromissoDao compromissoDao;
	 private Date currentDate;
	  public NotificacaoService(Usuario sessao) {
		  this.sessao = sessao;
		  compromissoDao = new CompromissoDao();
		 
	  }


	@Override
	public void run() {
		
		try {
			
			
			
			List<Compromisso> compromissos = compromissoDao.verificarCompromissos(sessao);

			if(compromissos!=null)
				
				for(Compromisso comp : compromissos) {
					
					 Toolkit.getDefaultToolkit().beep();	
					while(true) {
						
						 int resposta = JOptionPane.showConfirmDialog(null,
			                        "Você tem um compromisso as " + comp.getDataHoraInicio() + " Confirma?",
			                        "Notificação de Compromisso",
			                        JOptionPane.YES_NO_OPTION);

			                if (resposta == JOptionPane.YES_OPTION) {
			                   
			                    System.out.println("Compromisso confirmado!");
			                } else {
			                    
			                    System.out.println("Compromisso não confirmado!");
			                }

			          
			             
			                
						 Toolkit.getDefaultToolkit().beep();	
						
					}
					
					
				}	
			  try {
	                Thread.sleep(60000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
}
