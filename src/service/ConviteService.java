package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ConviteDao;
import entities.Agenda;
import entities.Compromisso;
import entities.Convite;
import entities.Usuario;

public class ConviteService {

	
	public boolean enviarConvites(int idUsuario, Compromisso comp) throws IOException, SQLException {
		ConviteDao bd = new ConviteDao();
		
		if(bd.cadastrarConvite(idUsuario, comp.getId())) {
			return true;
			
		}else {
			return false;
		}
		
		
	}
	
	
	public List<Convite> listarConvites(Usuario user) throws IOException, SQLException{


		
		ConviteDao bd = new ConviteDao();
		return bd.listarConvites(user.getId());
		
		
	}
	
	public boolean aceitarConvite(String op, int idConvite,Compromisso comp) throws IOException, SQLException {
		ConviteDao bd = new ConviteDao();
		
		
		if(bd.alterarStatusConvite(op,idConvite)) {
			CompromissoService compromissoService = new CompromissoService();
			
			compromissoService.cadastrarCompromisso(comp);
		}
			
		return true;
	
	}
	
	public boolean recusarConvite(String op, int idConvite) throws IOException, SQLException {
		ConviteDao bd = new ConviteDao();
		
		bd.alterarStatusConvite(op,idConvite);
		return true;
	}
	
	

}


