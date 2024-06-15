package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import dao.AgendaDao;
import dao.UsuarioDao;
import entities.Agenda;

public class AgendaService {

	public List<Agenda> listaAgendas(int id) throws IOException, SQLException{
		AgendaDao bd = new AgendaDao();
		
		return bd.listarAgendas(id);
	}
	
	public boolean criarAgenda(String nome, String descricao, int id) throws IOException, SQLException {
		AgendaDao bd = new AgendaDao();
		
		

		if(bd.cadastrarAgenda(nome,descricao,id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean atualizarAgenda(String nome, String descricao, int id) throws IOException, SQLException {
		
	AgendaDao bd = new AgendaDao();
		
		

		if(bd.atualizarAgenda(nome,descricao,id)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean apagarAgenda(int id) throws IOException, SQLException {
			AgendaDao bd = new AgendaDao();
		
		

		if(bd.apagarAgenda(id)) {
			return true;
		}
		else {
			return false;
		}
	}

}
