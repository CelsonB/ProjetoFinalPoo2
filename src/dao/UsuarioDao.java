package dao;

import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import entities.Usuario;

public class UsuarioDao  extends BancoDeDados{

	
	
	
	
	
	
	public List<Usuario> listarUsuarios() throws IOException, SQLException{
	
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("SELECT * FROM usuarios");
	
	
		
		ResultSet result= st.executeQuery();
		
		List<Usuario> listaTemp = new ArrayList<>();
		
		while(result.next()) {
			Usuario user = new Usuario();
			user.setId(result.getInt("id"));
			user.setDataNascimento(result.getDate("data_nascimento"));
			user.setNomeCompleto(result.getString("nome_completo"));
			user.setEmail(result.getString("email"));
			user.setSenha(result.getString("senha"));
			if(result.getString("genero").equals("M")) {
				user.setGenero(Usuario.Genero.M);
			}else {
				user.setGenero(Usuario.Genero.F);
			}
			user.setNomeUsuario(result.getString("nome_usuario"));
			listaTemp.add(user);
		}
		
		return listaTemp;
		
			
	}

	
	public int cadastrarUsuarios(Usuario userCadastro) throws IOException, SQLException {
		
		java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("Insert into usuarios(nome_completo,data_nascimento,genero,email,nome_usuario,senha) values (?,?,?,?,?,?)");
		st.setString(1, userCadastro.getNomeCompleto());
		st.setDate(2, new java.sql.Date(userCadastro.getDataNascimento().getTime())   );
		st.setString(3, userCadastro.getGenero().toString());
		st.setString(4,userCadastro.getEmail());
		st.setString(5,userCadastro.getNomeUsuario());
		st.setString(6,userCadastro.getSenha());
		int result= st.executeUpdate();
		
		if(result>0) {
			st = super.conn.prepareStatement("select id from usuarios where email = ? and senha = ? ");
			
			st.setString(1,userCadastro.getEmail());
			st.setString(2,userCadastro.getSenha());
			
			ResultSet rs = st.executeQuery();
			rs.next();
			
			int idUsuario = rs.getInt("id");
			
			
			st = super.conn.prepareStatement("INSERT INTO foto_usuario (foto_pessoal, id_usuario )values ( ? , ?)");
			
			if(userCadastro.getFotoPessoal()!=null) {
				st.setBytes(1, userCadastro.getFotoPessoal());
				st.setInt(2, idUsuario);
			}else if(st.executeUpdate()>0) {
				
			}
			return idUsuario;
			
		}else {
			return 0;
		}
	}
	

	
	public Usuario realizarLoginUsuario(Usuario userLogin) throws IOException, SQLException {
		Usuario user = new Usuario();
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("SELECT * FROM usuarios WHERE email = ? AND senha = ?");
	
		st.setString(1,userLogin.getEmail());
		st.setString(2,userLogin.getSenha());
	
		ResultSet result= st.executeQuery();
		if(result.next()) {
		
			System.out.println(result.toString());
			user.setId(result.getInt("id"));
			user.setDataNascimento(result.getDate("data_nascimento"));
			user.setNomeCompleto(result.getString("nome_completo"));
			user.setEmail(result.getString("email"));
			user.setSenha(result.getString("senha"));
			if(result.getString("genero").equals("M")) {
				user.setGenero(Usuario.Genero.M);
			}else {
				user.setGenero(Usuario.Genero.F);
			}
			user.setNomeUsuario(result.getString("nome_usuario"));
		
			System.out.println(user);
			return user;
			
		}else {
			System.out.println("Deu erro");
			return userLogin;
		}

	}
	
	public Usuario visualizarUsuario(int id ) throws IOException, SQLException {
		Usuario user = new Usuario();
		
		
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
	
		st.setInt(1,id);
		
	
		ResultSet result= st.executeQuery();
		
		if(result.next()) {
			System.out.println(result.toString());
			user.setId(result.getInt("id"));
			user.setDataNascimento(result.getDate("data_nascimento"));
			user.setNomeCompleto(result.getString("nome_completo"));
			user.setEmail(result.getString("email"));
			user.setSenha(result.getString("senha"));
			if(result.getString("genero").equals("M")) {
				user.setGenero(Usuario.Genero.M);
			}else {
				user.setGenero(Usuario.Genero.F);
			}
			user.setNomeUsuario(result.getString("nome_usuario"));
		
			return user;
		}else {
			return user;
		}

		
	}
	

	
	public BufferedImage visualizarFoto(int id) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();

		
		st = super.conn.prepareStatement("SELECT foto_pessoal FROM foto_usuario WHERE id_usuario = ?");
		
		st.setInt(1, id);
		ResultSet rs =  st.executeQuery();
		

		
			if(rs.next()) {
				 byte[] imageBytes = rs.getBytes("foto_pessoal");
				 if(imageBytes!=null) {
					  InputStream is = new ByteArrayInputStream(imageBytes);
					  BufferedImage img = ImageIO.read(is);
					  return img;
				 }
			}
			else 
			{
				
			}
			return null;
			
	}
	
	
	public boolean atualizarImagem(byte[] imageBytes, int id) throws IOException, SQLException {
		PreparedStatement st;
		super.Conectar();
		
		st = super.conn.prepareStatement("UPDATE foto_pessoal SET foto_usuario = ?  WHERE id_usuario = ?");
		st.setBytes(1,imageBytes);
		st.setInt(2, id);
		int rs =  st.executeUpdate();
		
		if(rs>0) {
			return true;
		}else {
			return false;
			
		}
			

	}
	
	
}
