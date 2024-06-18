package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.CompromissoDao;
import entities.Agenda;
import entities.Compromisso;

public class CompromissoService {

	
	public List<Compromisso> listaCompromisso(int id) throws SQLException, IOException {
		CompromissoDao bd = new CompromissoDao();
		Agenda agenda = new Agenda();
		agenda.setId(id);
		return bd.listarCompromissos(agenda);
	}
	
	public boolean cadastrarCompromisso(Compromisso compCadastro) throws SQLException, IOException {
		CompromissoDao bd = new CompromissoDao();
		
		if(bd.cadastrarCompromisso(compCadastro)) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	public boolean apagarCompromisso(Compromisso compApagar) throws IOException, SQLException {
		CompromissoDao bd = new CompromissoDao();
		if(bd.apagarCompromisso(compApagar)) {
			return true;
		}else {
			return false;
		}
		 
		
	}
}
