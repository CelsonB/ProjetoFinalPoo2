package service;

import entities.Usuario;

import java.io.IOException;
import java.sql.SQLException;

import dao.UsuarioDao;
public class UsuarioService {

	
	
	
	public void realizarCadastro(Usuario user) {
		UsuarioDao bd = new UsuarioDao();
		
		try {
			bd.cadastrarUsuarios(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
