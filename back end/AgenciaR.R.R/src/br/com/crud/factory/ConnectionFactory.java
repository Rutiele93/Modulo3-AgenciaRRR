package br.com.crud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	// nome do usuario do mysql
		private static final String USERNAME = "root";
	// senha do usuario do mysql
		private static final String PASSWORD = "060993";
	// Dados de caminho, porta e nome da base dados que ira ser feita a conexao
		private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agencia";
			/**
			 * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário e senha fornecidos 
			 * @param username
			 * @param senha
			 * @return uma conexão com o banco de dados
			 * @throws Exception
			 */
		public static Connection createConnectionToMySQL() throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");//faz com que a classe seja carregada pela JVM
			//Classe a conexao com o banco de dados
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			return connection;
		}
		
		public static void main(String[] args) throws Exception{
			// recupera um conexao com o banco de dados
			Connection con = createConnectionToMySQL();
			
			//testa se a conexao é nula
			if (con !=null) {
				System.out.println("Conexão obtida com sucesso!" + con);
				con.close();
			}

		}

	}

