-- Copiando estrutura para tabela pizzaria.tb_drinks
CREATE TABLE IF NOT EXISTS `tb_drinks` (
  `tipo` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKepo5o3rn329oylbcjtbyl0slg` FOREIGN KEY (`id`) REFERENCES `tb_products` (`id`)
)
