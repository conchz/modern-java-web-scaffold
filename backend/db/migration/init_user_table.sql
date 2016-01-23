DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `id`       CHAR(32)    NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `state`    TINYINT(1) DEFAULT 1,
  `birthday` DATE,
  `email`    VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE UNIQUE INDEX USER_username_uindex ON USER (username);