-- Copiando estrutura para tabela pizzaria.tb_pizzas
CREATE TABLE IF NOT EXISTS `tb_pizzas` (
  `sabor` varchar(255) NOT NULL,
  `ingredientes` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK171g1g7py4symvrus1p5v4odo` FOREIGN KEY (`id`) REFERENCES `tb_products` (`id`)
)
