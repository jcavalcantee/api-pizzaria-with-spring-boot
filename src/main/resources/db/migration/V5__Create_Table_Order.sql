-- Copiando estrutura para tabela pizzaria.tb_orders
CREATE TABLE IF NOT EXISTS `tb_orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_pedido` datetime(6) DEFAULT NULL,
  `tipo_pagamento` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `tb_orders_chk_1` CHECK ((`tipo_pagamento` between 0 and 3))
)
