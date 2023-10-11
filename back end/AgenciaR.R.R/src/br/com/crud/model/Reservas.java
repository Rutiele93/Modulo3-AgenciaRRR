package br.com.crud.model;
import java.util.Date;

public class Reservas extends Destino {

	    private int idReserva, idCliente, idDestino, quantReservada;
	    private Date dataReserva;
	    private String pagamento, statusPedido;
	    private float precoTotal;
	    
	  
	    public int getIdDestino() {
			return idDestino;
		}


		public void setIdDestino(int idDestino) {
			this.idDestino = idDestino;
		}


		public int getQuantReservada() {
			return quantReservada;
		}


		public void setQuantReservada(int quantReservada) {
			this.quantReservada = quantReservada;
		}


		public int getIdCliente() {
			return idCliente;
		}


		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}


		public int getIdReserva() {
			return idReserva;
		}


		public void setIdReserva(int idReserva) {
			this.idReserva = idReserva;
		}


		public Date getDataReserva() {
			return dataReserva;
		}


		public void setDataReserva(Date dataReserva) {
			this.dataReserva = dataReserva;
		}


		public String getPagamento() {
	        return pagamento;
	    }
	    
	      
	    public String getStatusPedido() {
	    	return statusPedido;
	    }
	    
	    public void setStatusPedido(String statusPedido) {
	    	this.statusPedido = statusPedido;
	    }
	    

	    public void setPagamento(String pagamento) {
	        this.pagamento = pagamento;
	    }
	    

	    public float getPrecoTotal() {
	        return precoTotal;
	    }
	    

	    public void setPrecoTotal(float precoTotal) {
	        this.precoTotal = precoTotal;
	    }
	    

	  
	    
	}

