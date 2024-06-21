package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Agenda;
import entities.Compromisso;
import entities.Usuario;

public class CompromissoDao extends BancoDeDados {

	
	public List<Compromisso> listarCompromissos(Agenda agenda) throws SQLException, IOException {
		List<Compromisso> listaCompromissos = new ArrayList<>();
		
		
		


		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("SELECT * FROM compromissos where id_agenda = ?");
		st.setInt(1, agenda.getId());
		ResultSet result = st.executeQuery();
		while(result.next()) {
			Compromisso compromisso = new Compromisso();
			compromisso.setId(result.getInt("id"));
			compromisso.setTitulo(result.getString("titulo"));
			compromisso.setDescricao(result.getString("descricao"));
			compromisso.setTitulo(result.getString("titulo"));
			compromisso.setDataHoraInicio(result.getTime("data_hora_inicio"));
			compromisso.setDataHoraTermino(result.getTime("data_hora_termino"));
			compromisso.setDataHoraNotificacao(result.getTime("data_hora_notificacao"));
			compromisso.setLocal(result.getString("local"));
			listaCompromissos.add(compromisso);
		}
		
		return listaCompromissos;
	}
	
	public boolean cadastrarCompromisso(Compromisso compromisso) throws SQLException, IOException {
		
		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("INSERT INTO COMPROMISSOS "
				+ "(titulo,descricao,data_hora_inicio,data_hora_termino,local,id_agenda,data_hora_notificacao)"
				+ "VALUES(?,?,?,?,?,?,?)");
		st.setString(1, compromisso.getTitulo());
		st.setString(2, compromisso.getDescricao());
		
		st.setTimestamp(3, new java.sql.Timestamp (compromisso.getDataHoraInicio().getTime()));
		st.setTimestamp(4, new java.sql.Timestamp (compromisso.getDataHoraTermino().getTime()));
		
		st.setString(5, compromisso.getLocal());
		st.setInt(6, compromisso.getAgenda().getId());
		st.setTimestamp(7, new java.sql.Timestamp (compromisso.getDataHoraNotificacao().getTime()));
		
		
		int result= st.executeUpdate();
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
		
		
		

	}
	public boolean apagarCompromisso(Compromisso compromisso) throws IOException, SQLException {
		
		
		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("DELETE FROM compromissos where id = ?");
		st.setInt(1, compromisso.getId());

		
		
		
		int result = st.executeUpdate();
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	public Compromisso visualizarCompromisso( Compromisso compromissoId) throws IOException, SQLException {
		Compromisso compromisso = new Compromisso();


		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("SELECT * FROM compromissos where id = ?");
		st.setInt(1, compromissoId.getId());

		ResultSet result = st.executeQuery();
		
		
		if(result.next()) {
			compromisso.setId(result.getInt("id"));
			compromisso.setTitulo(result.getString("titulo"));
			compromisso.setDescricao(result.getString("descricao"));
			compromisso.setTitulo(result.getString("titulo"));
			compromisso.setDataHoraInicio(result.getTime("data_hora_inicio"));
			compromisso.setDataHoraTermino(result.getTime("data_hora_termino"));
			compromisso.setDataHoraNotificacao(result.getTime("data_hora_notificacao"));
			compromisso.setLocal(result.getString("local"));
			
		}else {
			return null;
		}
		return compromisso;
	}
	
	public boolean atualizarCompromisso(Compromisso compromisso) throws IOException, SQLException {
		
		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement
				( " UPDATE COMPROMISSOS"
				+ " SET titulo =? ,descricao  =?  ,data_hora_inicio =? ,data_hora_termino =? ,local =? ,id_agenda =? ,data_hora_notificacao =? "
				+ " WHERE id = ?");
		st.setString(1, compromisso.getTitulo());
		st.setString(2, compromisso.getDescricao());
		st.setDate  (3, new java.sql.Date(compromisso.getDataHoraInicio().getTime()));
		st.setDate  (4, new java.sql.Date(compromisso.getDataHoraTermino().getTime()));
		st.setString(5, compromisso.getLocal());
		st.setInt   (6, compromisso.getAgenda().getId());
		st.setDate  (7, new java.sql.Date(compromisso.getDataHoraNotificacao().getTime()));
		st.setInt   (8, compromisso.getId());
		
		
		
		int result= st.executeUpdate();
		
		if(result>0) {
			return true;
		}else {
			return false;
		}
		
		

	}
	
	
	
	
	
	public List<Compromisso> verificarCompromissos(Usuario sessao) throws IOException, SQLException {
List<Compromisso> listaCompromissos = new ArrayList<>();
		
		
		


		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("SELECT * FROM compromissos INNER JOIN agendas ON agendas.id = compromissos.id_agenda WHERE id_usuario = ? AND data_hora_notificacao <= NOW()");
		st.setInt(1, sessao.getId());
		ResultSet result = st.executeQuery();
		
		while(result.next()) {
			Compromisso compromisso = new Compromisso();
			compromisso.setId(result.getInt("id"));
			compromisso.setTitulo(result.getString("titulo"));
			compromisso.setDescricao(result.getString("descricao"));
			compromisso.setTitulo(result.getString("titulo"));
			compromisso.setDataHoraInicio(result.getTime("data_hora_inicio"));
			compromisso.setDataHoraTermino(result.getTime("data_hora_termino"));
			compromisso.setDataHoraNotificacao(result.getTime("data_hora_notificacao"));
			compromisso.setLocal(result.getString("local"));
			listaCompromissos.add(compromisso);
		}
		
		return listaCompromissos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
