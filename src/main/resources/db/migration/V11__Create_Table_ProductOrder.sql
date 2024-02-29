-- Copiando estrutura para tabela pizzaria.tb_product_orders
CREATE TABLE IF NOT EXISTS `tb_product_orders` (
  `quantidade` int NOT NULL,
  `product_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `product` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FKoccww4ejpikc0fu7a28vtfgti` (`product_id`),
  CONSTRAINT `FKfrmr8celq7avxa0u1x58rxfeg` FOREIGN KEY (`order_id`) REFERENCES `tb_orders` (`id`),
  CONSTRAINT `FKoccww4ejpikc0fu7a28vtfgti` FOREIGN KEY (`product_id`) REFERENCES `tb_products` (`id`)
)


