package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.Passagens;

public class PassagensDAO {

	
	Connection conn = null;
	PreparedStatement pstm = null;

	public void save(Passagens passagem) {

		String sql = "INSERT INTO passagem(quantidade, idDestino)" + " VALUES(?,?)";

		try {

			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, passagem.getQuantidade());
			pstm.setInt(2, passagem.getIdDestino());
			
			pstm.execute();
			System.out.println("Passagem cadastrada com sucesso!");

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

		String sql = "DELETE FROM passagem WHERE ID_PASSAGEM = ?";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
		
			pstm.execute();
			System.out.println("Passagem excluída com sucesso!");

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

	public void update(Passagens passagem) {
		
		String sql = "UPDATE passagem SET quantidade = ? WHERE IdPassagem = ?";

		try {

			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, passagem.getQuantidade());
			pstm.setInt(2, passagem.getIdPassagem());
			
			pstm.execute();
			System.out.println("Passagem atualizada com sucesso!");

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
	

	public List<Passagens> getPassagens() {

		String sql = "SELECT * FROM passagem";

		List<Passagens> passagens = new ArrayList<Passagens>();
		
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {

				Passagens passagem = new Passagens();

				passagem.setQuantidade(rset.getInt("quantidade"));
				//passagem.setIdDestino(rset.getInt("idDestino"));

				
				passagens.add(passagem);
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

		return passagens;
	}

	
	public Passagens getPassagemById(int id) {

		String sql = "SELECT * FROM passagem where idPassagem = ?";
		Passagens passagem = new Passagens();

		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			rset.next();

			passagem.setQuantidade(rset.getInt("quantidade"));
			passagem.setIdDestino(rset.getInt("idPassagem"));
	

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
		return passagem;
  
    }

}

