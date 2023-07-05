CREATE TABLE IF NOT EXISTS customers (
                             id char(10) NOT NULL,
                             name varchar(40) NOT NULL,
                             email varchar(50) NOT NULL,
                             password varchar(20) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;