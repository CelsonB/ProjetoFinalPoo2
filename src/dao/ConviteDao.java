package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Agenda;
import entities.Compromisso;
import entities.Convite;
import entities.Usuario;

public class ConviteDao extends BancoDeDados{

	
	public boolean cadastrarConvite(int idUsuario, int idCompromisso) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("Insert into Convites(id_compromisso, id_usuario_convidado,status) values (?,?,?)");
		
		st.setInt(1, idCompromisso);
		st.setInt(2, idUsuario);
		st.setString(3,"pendente");
		int result= st.executeUpdate();
		
		
		if(result >0) {
			return true;
			
		}
			return false;
	}
	
	public List<Convite> listarConvites (int userId) throws IOException, SQLException {
	
		
		PreparedStatement st;
		super.Conectar();

		
		st = super.conn.prepareStatement("SELECT * FROM convites INNER JOIN compromissos ON convites.id_compromisso = compromissos.id WHERE id_usuario_convidado = ?");
		st.setInt(1, userId);
		ResultSet rs = st.executeQuery();
		
		List<Convite> listaConvites = new ArrayList<>();
		
		
		while(rs.next()) {
			Compromisso compromisso = new Compromisso();
			Convite convite = new Convite();
			convite.setStatus(rs.getString("status"));
			convite.setId(rs.getInt("convites.id"));
			
			compromisso.setId(rs.getInt("id_compromisso"));
			compromisso.setTitulo(rs.getString("titulo"));
			compromisso.setDescricao(rs.getString("descricao"));
			compromisso.setTitulo(rs.getString("titulo"));
			compromisso.setDataHoraInicio(rs.getTime("data_hora_inicio"));
			compromisso.setDataHoraTermino(rs.getTime("data_hora_termino"));
			compromisso.setDataHoraNotificacao(rs.getTime("data_hora_notificacao"));
			compromisso.setLocal(rs.getString("local"));
			
			convite.setCompromisso(compromisso);
			listaConvites.add(convite);
			
		}
		
		return listaConvites;
		
	
	}

	 
	public boolean alterarStatusConvite(String op, int id) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();

		
		st = super.conn.prepareStatement("UPDATE conviteS SET status = ? where id = ?");
		st.setString(1, op);
		st.setInt(2, id);
		int rs =  st.executeUpdate();
		

		
		if(rs>0) {
			return true;
		}else{
			return false;
		}
		
	}
	
	

}
