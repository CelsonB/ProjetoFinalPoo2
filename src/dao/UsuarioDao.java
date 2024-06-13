package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
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
	
	public void cadastrarUsuarios(Usuario userCadastro) throws IOException, SQLException {
		
		 java.util.Date utilDate = new java.util.Date();
		 
		PreparedStatement st;
		super.Conectar();
		st = super.conn.prepareStatement("Insert into usuarios(nome_completo,data_nascimento,genero,email,nome_usuario,senha) values (?,?,?,?,?,?)");
		st.setString(1, userCadastro.getNomeCompleto());
		st.setDate(2, new java.sql.Date(userCadastro.getDataNascimento().getTime())   );
		st.setString(3, userCadastro.getGenero().toString());
		st.setString(4,userCadastro.getEmail());
		st.setString(5,userCadastro.getNomeUsuario());
		st.setString(5,userCadastro.getSenha());
		st.executeUpdate();
	}
	
	
	
}
