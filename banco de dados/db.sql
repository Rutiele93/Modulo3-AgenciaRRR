


CREATE TABLE `clientes` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `telefone` varchar(10) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `senha` varchar(40) NOT NULL,
  `cadastrado` tinyint(1) DEFAULT NULL,
  `logado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `destino` (
  `nomeDestino` varchar(40) DEFAULT NULL,
  `categoriaDestino` varchar(40) DEFAULT NULL,
  `condicao` varchar(40) DEFAULT NULL,
  `precoUnit` float DEFAULT NULL,
  `idDestino` int NOT NULL AUTO_INCREMENT,
  `qtdDisponivel` int DEFAULT NULL,
  PRIMARY KEY (`idDestino`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reservas` (
  `idReserva` int NOT NULL AUTO_INCREMENT,
  `dataReserva` date DEFAULT NULL,
  `pagamento` varchar(40) NOT NULL,
  `precoTotal` float DEFAULT NULL,
  `idCliente` int DEFAULT NULL,
  `statusPedido` varchar(40) DEFAULT NULL,
  `idDestino` int DEFAULT NULL,
  `quantReservada` int DEFAULT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `idCliente` (`idCliente`),
  KEY `idDestino` (`idDestino`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`idDestino`) REFERENCES `destino` (`idDestino`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
