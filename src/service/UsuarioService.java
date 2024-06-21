package service;

import entities.Usuario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import dao.UsuarioDao;
public class UsuarioService {

	
	
	public List<Usuario> listarUsuarios() throws IOException, SQLException{
		UsuarioDao bd = new UsuarioDao();
		
		return bd.listarUsuarios();
	}
	
	
	
	
	public boolean realizarCadastro(Usuario user) throws IOException, SQLException {
		UsuarioDao bd = new UsuarioDao();
		
		

		int idUsuario  = bd.cadastrarUsuarios(user);
				if(idUsuario>0) {
					AgendaService agendaService = new AgendaService();
					
					agendaService.criarAgenda("Convites", "Todos os convites de compromisso do usuario", idUsuario );
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

		

		return bd.visualizarUsuario(id);
	}
	
	
	public boolean AtualizarImagem(File file, Usuario user) throws IOException, SQLException {
		
		
		
		UsuarioDao bd = new UsuarioDao();		
		
		FileInputStream fis = new FileInputStream(file);
		byte[] imageBytes = new byte[(int) file.length()];	
		fis.read(imageBytes);
		
		
		if(bd.atualizarImagem(imageBytes,user.getId())) {
			return true;
		}
		
		
		return false;
	}
	
	public BufferedImage pegarImagem(Usuario user) throws IOException, SQLException {
		UsuarioDao bd = new UsuarioDao();	
		return bd.visualizarFoto(user.getId());
	}
	
}

















