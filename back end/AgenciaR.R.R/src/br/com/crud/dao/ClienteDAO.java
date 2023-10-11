package br.com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.Cliente;

public class ClienteDAO {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	public void save(Cliente cliente) { // faz inclusao do cliente no banco de dados

		String sql = "INSERT INTO clientes(nomeCliente, cpf, telefone, email, senha, dataNascimento)" + "VALUES(?,?,?,?,?,?)";
		
		try {

			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getTelefone());			
			pstm.setString(4, cliente.getEmail());
			pstm.setString(5, cliente.getSenha());
			pstm.setDate(6, new Date(cliente.getDataNascimento().getTime()));
			
			pstm.execute();
				System.out.println("Cliente cadastrado com sucesso!");	
				
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
					//fecha as conexoes
							try {
					if (pstm != null) {
					pstm.close();
					}
					if (conn != null) {
					conn.close();
						}
					}catch (Exception e) {
					e.printStackTrace();
					}
					}
					}
	
	
		public void removeByCpf(String cpf)  { // faz exclusao do cliente atraves do cpf
		
		String sql = "DELETE FROM clientes WHERE cpf = ?";
			
			
			try {
				conn = ConnectionFactory.createConnectionToMySQL();				
				pstm = conn.prepareStatement(sql);					
				pstm.setString(1, cpf);
				
				pstm.execute();
				System.out.println("Cliente excluido com sucesso!");

				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			try {
			if(pstm != null) {
				
			pstm.close();
			}
			
			if (conn != null) {
			conn.close();
			}
			
			}catch(Exception e) {
			e.printStackTrace();
			}
			}
			}

	
	public void update(Cliente cliente) { // faz atualizacao do cadastro do cliente atraves do idCliente
		
		String sql = "UPDATE clientes SET nomeCliente = ?, telefone = ?, email = ?, senha = ? WHERE cpf = ?";
			
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNomeCliente());			
			pstm.setString(2, cliente.getTelefone());
			pstm.setDate(3, new Date(cliente.getDataNascimento().getTime()));
			pstm.setString(3, cliente.getEmail());
			pstm.setString(4, cliente.getSenha());			

			// campo ulizado para buscar o cadastro do cliente idCliente
			pstm.setString(5, cliente.getCpf());
			
			pstm.execute();
			System.out.println("Cliente atualizado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Cliente> getClientes(){ // select list <clientes>
		
		String sql = "SELECT * FROM clientes";
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();			
			pstm = conn.prepareStatement(sql);			
			rset = pstm.executeQuery();			
			
			//enquanto existir dados no banco de dados, faça
			while(rset.next()) {
				
			Cliente cliente = new Cliente();
		
			cliente.setIdCliente(rset.getInt("idCliente"));			
			cliente.setNomeCliente(rset.getString("nomeCliente"));			
			cliente.setTelefone(rset.getString("telefone"));
			cliente.setCpf(rset.getString("cpf"));
			cliente.setDataNascimento(rset.getDate("dataNascimento"));
			cliente.setEmail(rset.getString("email"));			
			cliente.setSenha(rset.getString("senha"));
			
			clientes.add(cliente);	
			
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				
			try {
			if (rset != null) {
			rset.close();
			}
			if (pstm != null) {
			pstm.close();
			}
			if (conn != null) {
			conn.close();
			}
			
			}catch(Exception e) {
				e.printStackTrace();
				}
				}
		return clientes;
	}	
	
	////////////////////////////////////////////////////////////////////////////////////
		

		public static Cliente buscarClientePorEmail(String email) { // codigo sobre busca de usuario

			String sql = "SELECT * FROM clientes WHERE email = ?";

			Cliente usuario = new Cliente();
			
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			try {
				conn = ConnectionFactory.createConnectionToMySQL();				
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, email);
				rset = pstm.executeQuery();
				
				rset.next();

				usuario.setIdCliente(rset.getInt("idCliente"));
				usuario.setCpf(rset.getString("cpf"));
				usuario.setNomeCliente(rset.getString("nomeCliente"));
				usuario.setTelefone(rset.getString("telefone"));
				usuario.setSenha(rset.getString("senha"));
				usuario.setDataNascimento(rset.getDate("dataNascimento"));
		
				
				
				// CÓDIGO ADD PARA FUNCIONAR A ATUALIZAÇÃO
				usuario.setEmail(rset.getString("email"));

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rset != null) {
						rset.close();
					}
					if (pstm != null) {
						pstm.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return usuario;
		}
		public Cliente getClienteByCpf(String cpf) {	// select do idCliente

			String sql = "SELECT * FROM clientes where cpf = ?";
			Cliente usuario = new Cliente();

			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			try {
				conn = ConnectionFactory.createConnectionToMySQL();				
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, cpf );
				rset = pstm.executeQuery();
				
				rset.next();

				usuario.setEmail(rset.getString("email"));
				usuario.setIdCliente(rset.getInt("idCliente"));
				usuario.setNomeCliente(rset.getString("nomeCliente"));
				usuario.setTelefone(rset.getString("telefone"));
				usuario.setSenha(rset.getString("senha"));
				usuario.setDataNascimento(rset.getDate("dataNascimento"));
	
				// CÓDIGO ADD PARA FUNCIONAR A ATUALIZAÇÃO
				usuario.setCpf(rset.getString("cpf"));

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rset != null) {
						rset.close();
					}
					if (pstm != null) {
						pstm.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return usuario;
		} 
	
	}
