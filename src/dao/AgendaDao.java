package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import entities.Agenda;

public class AgendaDao extends BancoDeDados {

	public List<Agenda> listarAgendas(int id) throws IOException, SQLException{
		List<Agenda> listaAgendas = new ArrayList<>();
		
		
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("Select * from Agendas where id_usuario = ?");
		st.setInt(1, id);
	
		ResultSet result= st.executeQuery();
		
		while(result.next()) {
			Agenda tempAgenda = new Agenda();
			tempAgenda.setId(result.getInt("id"));
			tempAgenda.setNome(result.getString("nome"));
			tempAgenda.setDescricao(result.getString("descricao"));
		
			
			listaAgendas.add(tempAgenda);
		}
		
		return listaAgendas;
	}
	
	
	public boolean cadastrarAgenda(String nome, String descricao, int id) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("Insert into agendas(nome,descricao,id_Usuario) values (?,?,?)");
		st.setString(1, nome);
		st.setString(2, descricao);
		st.setInt(3, id);
		int result= st.executeUpdate();
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean atualizarAgenda(String nome, String descricao, int id ) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("update Agendas set  nome = ? , descricao = ? WHERE id = ? ");
		st.setString(1, nome);
		st.setString(2, descricao);
		st.setInt(3, id);
		int result= st.executeUpdate();
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean apagarAgenda(int id) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("DELETE FROM agendas where id = ?");
		st.setInt(1, id);
		int result= st.executeUpdate();
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
