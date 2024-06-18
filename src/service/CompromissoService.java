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
		bd.cadastrarCompromisso(compCadastro);
		
		return true;
	}
}
