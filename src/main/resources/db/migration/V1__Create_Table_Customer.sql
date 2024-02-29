CREATE TABLE IF NOT EXISTS `tb_customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `rua` varchar(255) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `numero_casa` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bkul3pjw2320d1770qhpieo8x` (`telefone`)
)
