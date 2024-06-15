package service;

import entities.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import dao.UsuarioDao;
public class UsuarioService {

	
	
	
	public boolean realizarCadastro(Usuario user) throws IOException, SQLException {
		UsuarioDao bd = new UsuarioDao();
		
		

				if(bd.cadastrarUsuarios(user)) {
					return true;
				}
				else {
					return false;
				}
		
			
		
		
	}
	
	public Usuario realizarLoginEmail(Usuario userLogin) throws IOException, SQLException {
		UsuarioDao bd = new UsuarioDao();		
		Usuario user = new Usuario();
		user = bd.realizarLoginUsuario(userLogin);
		System.out.println(user);
		return user;
			 

	}
	
	public Usuario visualizarUsuario(int id) throws IOException, SQLException {
		UsuarioDao bd = new UsuarioDao();		
//		String email = user.getEmail();
//		String senha = user.getSenha();
		

		return bd.visualizarUsuario(id);
	}
	
	
}

















