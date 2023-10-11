package br.com.crud.model;

import java.util.Date;


public class Cliente {
	
	private int idCliente;
	private String nomeCliente;
	private String cpf;
	private String email;
	private String senha; 
	private String telefone;
	private Date dataNascimento;
	private Boolean cadastrado, logado;
	
	
	/* public Cliente(int idCliente, String nomeCliente, String cpf, String email, String senha, String telefone, Date dataNascimento, Boolean cadastrado, Boolean logado) {
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.cadastrado = cadastrado;
		this.logado = logado;
	} */

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getCadastrado() {
		return cadastrado;
	}

	public void setCadastrado(Boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	public Boolean getLogado() {
		return logado;
	}

	public void setLogado(Boolean logado) {
		this.logado = logado;
	}
	
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", cpf=" + cpf + ", email=" + email
				+ ", senha=" + senha + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", cadastrado="
				+ cadastrado + ", logado=" + logado + "]";
	}
	
	public void finalizarCompra() { //metodo usuario
       
    } 

    public void cancelarCompra() { //metodo usuario
       
    }

}
