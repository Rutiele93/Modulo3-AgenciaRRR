package br.com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.Reservas;

public class ReservasDAO {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	public void save(Reservas reservas) {

		String sql = "INSERT INTO reservas(dataReserva, idCliente, idDestino, quantReservada, precoTotal,"
				+ "statusPedido, pagamento) VALUES(?,?,?,?,?,?,?)";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setDate(1, new Date(reservas.getDataReserva().getTime()));
			pstm.setInt(2, reservas.getIdCliente());
			pstm.setInt(3, reservas.getIdDestino());
			pstm.setInt(4, reservas.getQuantReservada());
			pstm.setFloat(5, reservas.getPrecoTotal());
			pstm.setString(6,reservas.getStatusPedido());
			pstm.setString(7,reservas.getPagamento());
					
			
			pstm.execute();
			System.out.println("Reserva salva com sucesso!");

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

		String sql = "DELETE FROM reservas WHERE idReserva = ?";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
		
			pstm.execute();
			System.out.println("Pedido excluído com sucesso!");

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


	public void update(Reservas pedido) {		
		
		String sql = "UPDATE reservas SET precoTotal = ?, pagamento = ?, statusPedido = ?, "
				+ "quantReservada = ? WHERE idReserva = ?";
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
						
			pstm.setFloat(1, pedido.getPrecoTotal());
			pstm.setString(2, pedido.getPagamento());
			pstm.setString(3, pedido.getStatusPedido());			
			pstm.setInt(4, pedido.getQuantReservada());
			
			// CAMPO QUE SERÁ UTILIZADO PARA BUSCAR O CADASTRO
			pstm.setInt(5, pedido.getIdReserva());
			
			pstm.execute();
			System.out.println("RESERVA atualizado com sucesso!");

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
	
	public List<Reservas> getPedidos() {
		String sql = "SELECT * FROM reservas";

		List<Reservas> pedidos = new ArrayList<Reservas>();
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {

				Reservas pedido = new Reservas();

				pedido.setIdReserva(rset.getInt("idReserva"));
				pedido.setDataReserva(rset.getDate("dataReserva"));
				pedido.setIdCliente(rset.getInt("idCliente"));
				pedido.setIdDestino(rset.getInt("idDestino"));
				pedido.setQuantReservada(rset.getInt("quantReservada"));
				pedido.setPrecoTotal(rset.getFloat("precoTotal"));				
				pedido.setStatusPedido(rset.getString("statusPedido"));
				pedido.setPagamento(rset.getString("pagamento"));
				
				
				pedidos.add(pedido);
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

		return pedidos;
	}

	
	public Reservas getPedidoById(int id) {

		String sql = "SELECT * FROM reservas where idReserva = ?";
		Reservas reservas = new Reservas();

		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			rset.next();
			
			reservas.setIdReserva(rset.getInt("idReserva"));			
			reservas.setIdCliente(rset.getInt("idCliente"));
			reservas.setIdDestino(rset.getInt("idDestino"));
			reservas.setQuantReservada(rset.getInt("quantReservada"));
			reservas.setDataReserva(rset.getDate("DataReserva"));
			reservas.setPrecoTotal(rset.getFloat("precoTotal"));						
			reservas.setPagamento(rset.getString("pagamento"));
			reservas.setStatusPedido(rset.getString("statusPedido"));
			
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
		return reservas;
  
    }

}
