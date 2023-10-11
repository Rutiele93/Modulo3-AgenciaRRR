import br.com.crud.model.Cliente;
import br.com.crud.model.Destino;
import br.com.crud.model.Reservas;

import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import br.com.crud.dao.ClienteDAO;
import br.com.crud.dao.ReservasDAO;

import br.com.crud.dao.DestinoDAO;

public class Principal {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		// classes DAO

		ClienteDAO clienteDAO = new ClienteDAO();
		ReservasDAO reservaDAO = new ReservasDAO();
		DestinoDAO destinoDAO = new DestinoDAO();
		
		// classes model
	
		Reservas reserva = new Reservas();		
		Cliente cliente = new  Cliente ();
		Destino destino = new Destino ();
	
		// variaveis id geral
		int id;

		// variavel cadastro loop
		int opcao = 0;
				
		// variavel menu
		int menu = 0;
	
		Scanner entrada = new Scanner(System.in);
		
		
		System.out.println("=== INFORME ABAIXO ONDE DESEJA REALIZAR ===");
		System.out.println("=> 1 - PORTAL CLIENTE");
		System.out.println("=> 2 - PORTAL DESTINO");
		System.out.println("=> 3 - PORTAL RESERVA");
		
		menu = entrada.nextInt();
		
		switch (menu) {
		
		case 1:{ // portal do cliente
			
			do {
				System.out.println("===== BEM VINDOS AO MENU CLIENTE =====");
				System.out.println("1 - Cadastro de cliente");
				System.out.println("2 - Excluir cadastro");
				System.out.println("3 - Atualizar cadastro");
				System.out.println("4 - Mostrar cadastro");
				System.out.println("5 - Buscar por CPF");
				System.out.println("6 - Realizar Login");
				System.out.println("7 - Sair");
				opcao = entrada.nextInt();
			
			switch (opcao) {
			
				case 1:{
					
					System.out.println("===== REALIZANDO CADASTRO DE CLIENTE =====");
					
					System.out.println("Digite o NOME do cliente:");
					String nome = entrada.next();
					cliente.setNomeCliente(nome);
					
					System.out.println("Digite o CPF do cliente:");
					String cpf = entrada.next();
					cliente.setCpf(cpf);
					
					System.out.println("Digite o TELEFONE do cliente:");
					String telefone = entrada.next();
					cliente.setTelefone(telefone);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy"); 
					System.out.println("Digite a DATA NASCIMENTO do cliente:");
					String dataNascimentoStr = entrada.next();		     
					Date dataNascimento = sdf.parse(dataNascimentoStr);					
					cliente.setDataNascimento(dataNascimento);
										

					System.out.println("Cadastre o E-MAIL do cliente:");
					String email = entrada.next();
					cliente.setEmail(email);
					
					System.out.println("Cadastre a SENHA para login do cliente:");
					String senha = entrada.next();
					cliente.setSenha(senha);					
					
					clienteDAO.save (cliente);				
					
					break;	
				}
				
				case 2: {
					System.out.println("===== REALIZANDO EXCLUSAO DE CLIENTE ATRAVES DO CPF =====");
					System.out.println("Digite o CPF do cliente para exclusao: ");
					try {						
						String cpf = entrada.next();						
						clienteDAO.removeByCpf(cpf);
						
					} catch (Exception e) {
						// e.getMessage();						 
						System.out.println("Nenhum contato para excluir ");
					}					
					break;
				}
				
				case 3:{
					System.out.println("===== REALIZANDO ATUALIZACAO DO CADASTRO CLIENTE =====");
					
					System.out.println("Digite o CPF do contato para atualizar: ");
					String cpf = entrada.next();
				
					System.out.println("Digite o novo NOME do cliente: ");
					String nome = entrada.next();
					cliente.setNomeCliente(nome);						
					
					System.out.println("Digite o novo TELEFONE do cliente: ");
					String telefone = entrada.next();
					cliente.setTelefone(telefone);
					
					System.out.println("Digite o novo E-MAIL do cliente: ");
					String email = entrada.next();
					cliente.setEmail(email);
					
					System.out.println("Cadastre a SENHA para login do cliente:");
					String senha = entrada.next();
					cliente.setSenha(senha);
					
					cliente.setCpf(cpf);
					clienteDAO.update(cliente);					
					
					break;	
				}
				
				case 4: {
					System.out.println(" === MOSTRAR CADASTROS DOS CLIENTES === ");
					
					for (Cliente cli : clienteDAO.getClientes()) {
						
						System.out.println("ID CLIENTE: " + cli.getIdCliente());
						System.out.println("NOME: " + cli.getNomeCliente());
						System.out.println("TELEFONE: " + cli.getTelefone());
						System.out.println("CPF: " + cli.getCpf());
						System.out.println("DATA NASCIMENTO: " + cli.getDataNascimento());
						System.out.println("EMAIL: " + cli.getEmail());						
						System.out.println("----------------------------------- ");
					}					
					break;
				}
				
				case 5:{
					System.out.println(" === REALIZANDO BUSCA ATRAVES DO CPF === ");
					System.out.print("Digite o CPF para buscar: ");
					String cpf = entrada.next();
					
					
					Cliente cli = new Cliente();
					cli = clienteDAO.getClienteByCpf(cpf);
					
					System.out.println("ID CLIENTE: " + cli.getIdCliente());
					System.out.println("NOME: " + cli.getNomeCliente());
					System.out.println("EMAIL: " + cli.getEmail());
					System.out.println("TELEFONE: " + cli.getTelefone());
					System.out.println("CPF: " + cli.getCpf());
					System.out.println("DATA NASCIMENTO: " + cli.getDataNascimento());
					System.out.println("EMAIL: " + cli.getEmail());	
					System.out.println("----------------------------------- ");
					
					
					break;	
				}
				
				case 6: {
				System.out.println(" === REALIZANDO LOGIN DO USUARIO === ");
				
				System.out.print("Digite o EMAIL do usuario: ");
				String email = entrada.next();
				
				System.out.print("Digite a SENHA do usuario: ");
				String senha = entrada.next();
								
				Cliente u = new Cliente();

				u = ClienteDAO.buscarClientePorEmail(email);
				
				boolean logado = false;
				
				if(u != null && u.getSenha().equals(senha)) {					
					logado = true;											
				} 
				
				if (logado) {			
					
					System.out.println("== USUÁRIO LOGADO ==");

					System.out.println("ID: " + u.getIdCliente());
					System.out.println("CPF: " + u.getCpf());
					System.out.println("NOME: " + u.getNomeCliente());
					System.out.println("EMAIL: " + u.getEmail());
					System.out.println("SENHA: " + u.getSenha());			
					System.out.println("----------------------------------- ");
					
				} else {
					
					logado = false; 
					
					System.out.println("USUÁRIO LOGADO: " + u.getLogado());
					System.out.println("E-mail e/ou senha incorretos.");
				}			

				break;	
				
				}	
				
				case 7: {
					System.out.println(" === AGRADECEMOS A SUA PREFERENCIA! === ");
					break;
				}
				default:
					System.out.println("OPCAO INVALIDA: ");	
				};
					
				} while (opcao != 8);
								
				break;
	
				}
		
		
		// DESTINO
		case 2: { // DESTINO
			
			do {
				System.out.println("===== DESTINO =====");
				System.out.println("1 - Cadastro de destino");
				System.out.println("2 - Excluir cadastro");
				System.out.println("3 - Atualizar cadastro");
				System.out.println("4 - Mostrar cadastro");
				System.out.println("5 - Buscar cadastro por id");
				System.out.println("6 - Sair");
				opcao = entrada.nextInt();

				switch (opcao) {
				
				case 1: { // ("1 - Cadastro de destino");
					
					System.out.println("Digite o NOME do destino:");
					String nomeDestino = entrada.next();
					destino.setNomeDestino(nomeDestino);
					
					System.out.println("Digite o PREÇO do destino:");
					Float precoDestino = entrada.nextFloat();
					destino.setPrecoUnit(precoDestino);

					System.out.println("Digite a Classe do destino (A), (B) OU (C):");
					String categoriaDestino = entrada.next();
					destino.setCategoriaDestino(categoriaDestino);
					
					System.out.println("Digite a QUANTIDADE:");
					int qtdDisponivel = entrada.nextInt();
					destino.setQtdDisponivel(qtdDisponivel);
					
					System.out.println("Digite a CONDIÇÃO VOO TIPO (DIRETO) OU (ESCALA):");
					String condicao = entrada.next();
					destino.setCondicao(condicao);
					
					destinoDAO.save(destino);
					
					break;
				}
				case 2: { // ("2 - Excluir cadastro");
					
					System.out.println("Digite o id do destino para exclusao: ");
					try {						
						id = entrada.nextInt();						
						destinoDAO.removeById(id);
						
					} catch (Exception e) {
						// e.getMessage();						 
						System.out.println("Nenhum destino para excluir ");
					}
					break;
				}
				case 3: { // ("3 - Atualizar cadastro");					
					
					System.out.println("SEGUE LISTA DE DISTINOS CADASTRADOS PARA ATUALIZAÇÃO ");
					
					for (Destino des : destinoDAO.getDestinos()) {						
						System.out.println("ID DESTINO: " + des.getIdDestino());
						System.out.println("NOME DESTINO: " + des.getNomeDestino());
						System.out.println("PRECO R$: " + des.getPrecoUnit());
						System.out.println("CLASSE: " + des.getCategoriaDestino());
						System.out.println("QUANTIDADE DISPONÍVEL: " + des.getQtdDisponivel());			
						System.out.println("CONDIÇÃO DO VOO: " + des.getCondicao());
						System.out.println("----------------------------------- ");
					}
					
					System.out.println(" **** INFORME QUAL DESTINO DESEJA ATUALIZAR **** ");
					System.out.println("Digite o id do destino para atualizar: ");
					id = entrada.nextInt();

					System.out.println("Digite o novo NOME do destino: ");
					String nomeDestino = entrada.next();
					destino.setNomeDestino(nomeDestino);
									
					System.out.println("Digite o NOVO PREÇO: ");
					float precoDestino = entrada.nextFloat();
					destino.setPrecoUnit(precoDestino);;
					
					System.out.println("Digite a nova Classe do destino (A), (B) OU (C): ");
					String categoriaDestino = entrada.next();
					destino.setCategoriaDestino(categoriaDestino);
					
					System.out.println("Digite a nova QUANTIDADE DISPONÍVEL para destino: ");
					int qtdDisponivel = entrada.nextInt();
					destino.setQtdDisponivel(qtdDisponivel);
					
					System.out.println("Digite a nova CONDIÇÃO DO VOO TIPO (DIRETO) OU (ESCALA): ");
					String condicao = entrada.next();
					destino.setCondicao(condicao);
					

					destino.setIdDestino(id);
					destinoDAO.update(destino);
				}
				case 4: { // ("4 - Mostrar cadastro");
					
					for (Destino des : destinoDAO.getDestinos()) {
						
						System.out.println("ID DESTINO: " + des.getIdDestino());
						System.out.println("NOME DESTINO: " + des.getNomeDestino());
						System.out.println("PRECO R$: " + des.getPrecoUnit());
						System.out.println("CLASSE: " + des.getCategoriaDestino());
						System.out.println("QUANTIDADE DISPONÍVEL: " + des.getQtdDisponivel());			
						System.out.println("CONDIÇÃO DO VOO: " + des.getCondicao());
						
						System.out.println("----------------------------------- ");

					}
					break;
				}
				case 5: { // ("5 - Buscar cadastro por id");

					System.out.print("Digite o id para buscar: ");
					id = entrada.nextInt();
					
					Destino des = new Destino();

					des = destinoDAO.getDestinoById(id);

					System.out.println("NOME DESTINO: " + des.getNomeDestino());
					System.out.println("PRECO R$: " + des.getPrecoUnit());
					System.out.println("CLASSE: " + des.getCategoriaDestino());
					System.out.println("QUANTIDADE DISPONÍVEL: " + des.getQtdDisponivel());			
					System.out.println("CONDIÇÃO DO VOO: " + des.getCondicao());

					System.out.println("----------------------------------- ");
				}
					break;

				case 6: {
					System.out.println(" === Até logo! === ");
					break;
				}
				default:
					System.out.println("Opcao invalida: ");
				};

			} while (opcao != 6);			
			break;
			}		

			
		case 3: {  	// PEDIDO
			
			do {
				System.out.println("===== RESERVA =====");
				System.out.println("1 - Cadastro de RESERVA");
				System.out.println("2 - Excluir RESERVA");
				System.out.println("3 - Atualizar RESERVA");
				System.out.println("4 - Mostrar RESERVA");
				System.out.println("5 - Buscar RESERVA pelo número");
				System.out.println("6 - Sair");
				opcao = entrada.nextInt();

				switch (opcao) {
				
				case 1: { //("1 - Cadastro de reserva");
					
					reserva.setDataReserva(new Date());		
					
					System.out.println("Digite o id do CLIENTE:");
					id = entrada.nextInt();
					reserva.setIdCliente(id);				
					
					System.out.print("Digite o id Destino para Reservar: ");
						id = entrada.nextInt();					
					Destino des = new Destino();
					des = destinoDAO.getDestinoById(id);					
					System.out.println(" --- DESTINO SELECIONADO --- ");
					System.out.println("NOME DESTINO: " + des.getNomeDestino());
					System.out.println("PRECO R$: " + des.getPrecoUnit());					
					System.out.println("QUANTIDADE DISPONÍVEL: " + des.getQtdDisponivel());
					reserva.setIdDestino(id);
					
					System.out.println("Digite a QUANTIDADE para RESERVAR:");
					int qtd = entrada.nextInt();
					reserva.setQuantReservada(qtd);
					
					System.out.println("Digite o PREÇO TOTAL do RESERVA:");
					Float total = entrada.nextFloat();
					reserva.setPrecoTotal(total);
					
					System.out.println("Digite O STATUS DA RESERVA (APROVADO) (NEGADO):");
					String status = entrada.next();
					reserva.setStatusPedido(status);					
					
					System.out.println("Digite a FORMA DE PAGAMENTO (AVISTA) (PIX) OU (CARTAO):");
					String formaPagamento = entrada.next();
					reserva.setPagamento(formaPagamento);
					
					reservaDAO.save(reserva);
					break;
				}
				case 2: { //("2 - Excluir pedido");
					
					System.out.println("Digite o NÚMERO DO RESERVA para exclusao: ");
					try {
						
						id = entrada.nextInt();
						
						reservaDAO.removeById(id);
						
					} catch (Exception e) {
						// e.getMessage();						 
						System.out.println("Nenhum pedido para excluir ");
					}

					break;
				}
				case 3: { //("3 - Atualizar pedido");
					
					System.out.println("SEGUE LISTA DE RESERVAS CADASTRADAS PARA ATUALIZAÇÃO ");
					
					for (Reservas ped : reservaDAO.getPedidos()) {
						System.out.println("ID RESERVA: " + ped.getIdReserva());
						System.out.println("ID CLIENTE: " + ped.getIdCliente());
						System.out.println("ID DESTINO: " + ped.getIdDestino());
						System.out.println("QUANTIDADE RESERVADA: " + ped.getQuantReservada());
						System.out.println("PREÇO TOTAL R$: " + ped.getPrecoTotal());
						System.out.println("FORMA DE PAGAMENTO: " + ped.getPagamento());
						System.out.println("DATA DO PEDIDO: " + ped.getDataReserva());
						System.out.println("STATUS RESERVA: " + ped.getStatusPedido());
						
						System.out.println("----------------------------------- ");
					}
					
					System.out.println(" **** INFORME QUAL RESERVA DESEJA ATUALIZAR **** ");
					System.out.println("Digite o ID DO RESERVA para atualizar: ");
					id = entrada.nextInt();
										
					System.out.println("Qual NOVA QUANTIDADE deseja RESERVAR? ");
					int qtd = entrada.nextInt();
					reserva.setQuantReservada(qtd);
					
					System.out.println("Digite o novo PREÇO do RESERVA: ");
					float total = entrada.nextFloat();
					reserva.setPrecoTotal(total);
					
					System.out.println("Digite a nova FORMA DE PAGAMENTO (AVISTA) (PIX) OU (CARTAO): ");
					String formaPagamento = entrada.next();
					reserva.setPagamento(formaPagamento);
					
					System.out.println("Digite o novo STATUS DA RESERVA (APROVADO) (NEGADO): ");
					String status = entrada.next();
					reserva.setStatusPedido(status);							
										
					reserva.setIdReserva(id);
					reservaDAO.update(reserva);
				}
				case 4: { // ("4 - Mostrar pedido");
					
					for (Reservas ped : reservaDAO.getPedidos()) {
						System.out.println("ID RESERVA: " + ped.getIdReserva());
						System.out.println("ID CLIENTE: " + ped.getIdCliente());
						System.out.println("ID DESTINO: " + ped.getIdDestino());
						System.out.println("QUANTIDADE RESERVADA: " + ped.getQuantReservada());
						System.out.println("PREÇO TOTAL R$: " + ped.getPrecoTotal());
						System.out.println("FORMA DE PAGAMENTO: " + ped.getPagamento());
						System.out.println("DATA DO PEDIDO: " + ped.getDataReserva());
						System.out.println("STATUS RESERVA: " + ped.getStatusPedido());						
						System.out.println("----------------------------------- ");
					}
					break;
				}
				case 5: { // ("5 - Buscar pedido pelo número");

					System.out.print("Digite o NÚMERO do RESERVA para buscar: ");
					id = entrada.nextInt();
					
					Reservas ped = new Reservas();

					ped = reservaDAO.getPedidoById(id);
					
					System.out.println("*** SEGUE LISTA DE RESERVAS SELECIONADA ***");
					System.out.println("ID RESERVA: " + ped.getIdReserva());
					System.out.println("ID CLIENTE: " + ped.getIdCliente());
					System.out.println("ID DESTINO: " + ped.getIdDestino());
					System.out.println("QUANTIDADE RESERVADA: " + ped.getQuantReservada());
					System.out.println("PREÇO TOTAL R$: " + ped.getPrecoTotal());
					System.out.println("FORMA DE PAGAMENTO: " + ped.getPagamento());
					System.out.println("DATA DO PEDIDO: " + ped.getDataReserva());
					System.out.println("STATUS RESERVA: " + ped.getStatusPedido());

					System.out.println("----------------------------------- ");
				}
					break;

				case 6: {
					System.out.println(" === Agradecemos pela preferência! === ");
					break;
				}
				default:
					System.out.println("Opcao invalida: ");
				};

			} while (opcao != 6);
			
			
			break;

			}	

		}

	entrada.close();

}

}


