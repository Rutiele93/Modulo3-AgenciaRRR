package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.Destino;

public class DestinoDAO {
	Connection conn = null;
	PreparedStatement pstm = null;

	public void save(Destino destino) {


		String sql = "INSERT INTO destino(nomeDestino, precoUnit, categoriaDestino,"
				+ " qtdDisponivel, condicao)" + " VALUES(?,?,?,?,?)";

		try {

			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, destino.getNomeDestino());
			pstm.setFloat(2, destino.getPrecoUnit());
			pstm.setString(3, destino.getCategoriaDestino());
			pstm.setInt(4, destino.getQtdDisponivel());
			pstm.setString(5, destino.getCondicao());
			
			pstm.execute();
			System.out.println("Destino adicionado com sucesso!");

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

	public void removeById(int id) {

		String sql = "DELETE FROM destino WHERE idDestino = ?";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
		
			pstm.execute();
			System.out.println("Destino excluído com sucesso!");

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


	public void update(Destino destino) {		
		String sql = "UPDATE destino SET nomeDestino = ?, precoUnit = ?,"
				+ " categoriaDestino = ?, qtdDisponivel = ?, condicao = ?"
		+ " WHERE idDestino = ?";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, destino.getNomeDestino());
			pstm.setFloat(2, destino.getPrecoUnit());
			pstm.setString(3, destino.getCategoriaDestino());
			pstm.setInt(4, destino.getQtdDisponivel());
			pstm.setString(5, destino.getCondicao());

			// CAMPO QUE SERÁ UTILIZADO PARA BUSCAR O CADASTRO
			pstm.setInt(6, destino.getIdDestino());
			

			pstm.execute();
			System.out.println("Destino atualizado com sucesso!");

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
	

	public List<Destino> getDestinos() {

		String sql = "SELECT * FROM destino";

		List<Destino> destinos = new ArrayList<Destino>();

		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();


			while (rset.next()) {

				Destino destino = new Destino();
				
				destino.setIdDestino(rset.getInt("idDestino"));
				destino.setNomeDestino(rset.getString("nomeDestino"));
				destino.setPrecoUnit(rset.getFloat("precoUnit"));
				destino.setCategoriaDestino(rset.getString("categoriaDestino"));				
				destino.setQtdDisponivel(rset.getInt("qtdDisponivel"));				
				destino.setCondicao(rset.getString("condicao"));
				
				destinos.add(destino);
			}
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

		return destinos;
	}

	
	public Destino getDestinoById(int id) {

		String sql = "SELECT * FROM destino where idDestino = ?";
		Destino destino = new Destino();

		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			rset.next();

			destino.setNomeDestino(rset.getString("nomeDestino"));
			destino.setPrecoUnit(rset.getFloat("precoUnit"));
			destino.setCategoriaDestino(rset.getString("categoriaDestino"));
			destino.setQtdDisponivel(rset.getInt("qtdDisponivel"));
			destino.setCondicao(rset.getString("condicao"));
			

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
		return destino;
  
    }

	
}
