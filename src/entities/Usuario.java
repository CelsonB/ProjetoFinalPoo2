package entities;

import java.util.Date;

public class Usuario {
    private int id;
    private String nomeCompleto;
    private Date dataNascimento;
    private Genero genero;
    //private byte[] fotoPessoal; depois eu resolvo essa parte 
    private String email;
    private String nomeUsuario;
    private String senha;

    // Construtores
    public Usuario() {}

    public Usuario(String nomeCompleto, Date dataNascimento, Genero genero, String email, String nomeUsuario, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

//    public byte[] getFotoPessoal() {
//        return fotoPessoal;
//    }
//
//    public void setFotoPessoal(byte[] fotoPessoal) {
//        this.fotoPessoal = fotoPessoal;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    @Override
	public String toString() {
		return "Usuario [id=" + id + ", nomeCompleto=" + nomeCompleto + ", dataNascimento=" + dataNascimento
				+ ", genero=" + genero + ", email=" + email + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha + "]";
	}


	public enum Genero {
        M, F
    }
}