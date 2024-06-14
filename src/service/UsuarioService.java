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
	
	public boolean realizarLoginEmail(Usuario userLogin) {
		UsuarioDao bd = new UsuarioDao();		
		
		try {
			if(userLogin.getEmail()==null && bd.realizarLoginUsuario(userLogin)) {
					return true;
			}else if(bd.realizarLogin(userLogin)){
				
				return true;
			}else {	
				return false;
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return false;

	}
	
	public Usuario visualizarUsuario(Usuario user) throws IOException, SQLException {
		UsuarioDao bd = new UsuarioDao();		
		String email = user.getEmail();
		String senha = user.getSenha();
		

		return bd.visualizarUsuario(email, senha);
	}
	
	
}

















