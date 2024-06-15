package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Usuario;

public class UsuarioDao  extends BancoDeDados{

	
	
	
//	CREATE TABLE usuarios (
//			  id INT PRIMARY KEY auto_increment,
//			  nome_completo VARCHAR(100) NOT NULL,
//			  data_nascimento DATE NOT NULL,
//			  genero ENUM('M', 'F') NOT NULL,
//			  foto_pessoal BLOB,
//			  email VARCHAR(100) NOT NULL,
//			  nome_usuario VARCHAR(50) NOT NULL UNIQUE,
//			  senha VARCHAR(255) NOT NULL
//			);
	
	public boolean cadastrarUsuarios(Usuario userCadastro) throws IOException, SQLException {
		
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
			return true;
		}else {
			return false;
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
			if(result.getString("genero")=="M") {
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
			if(result.getString("genero")=="M") {
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
	
	
	
}
