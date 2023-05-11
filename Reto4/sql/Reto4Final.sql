CREATE DATABASE IF NOT EXISTS reto4; 
use reto4;

CREATE TABLE IF NOT EXISTS`admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT Primary key,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS `modos` (
  `id` int(11) NOT NULL AUTO_INCREMENT Primary key,
  `nombre` varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT primary key,
  `name` varchar(50) NOT NULL,
  `password_hash` varchar(100) NOT NULL,
  `registration_date`  varchar(10) NOT NULL,
  `level` int(11) NOT NULL,
  `rank` varchar(10) NOT NULL,
  `bloqueado` tinyint(1) DEFAULT NULL
);

CREATE TABLE if not exists `champions` (
  `id` int(11) NOT NULL AUTO_INCREMENT primary key,
  `name` varchar(50) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `ability_power` int(11) DEFAULT NULL,
  `life` int(11) DEFAULT NULL,
  `mana` int(11) DEFAULT NULL
);

CREATE TABLE if not exists`matches` (
  `id` int(11) NOT NULL AUTO_INCREMENT primary key,
  `date` varchar(10) NOT NULL,
  `duration` int(11) NOT NULL,
  `result` tinyint(1) NULL,
  `Estadisticas` varchar(100) NOT NULL,
  `champion_id` int(11) NOT NULL,
  `modo_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  	FOREIGN KEY (champion_id) REFERENCES champions(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (modo_id) REFERENCES modos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE table IF NOT EXISTS `abilities`(
`id` int(11) NOT NULL AUTO_INCREMENT Primary key,
`champion_id` int(11) not null,
`name` varchar(50) DEFAULT NULL,
`description` text DEFAULT NULL,
FOREIGN KEY (champion_id) REFERENCES champions(id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `admins` (`id`, `name`, `password`) VALUES
(1, 'David','davidcontra'),
(2, 'Gaizka', 'gaizkacontra'),
(3,'Unax','unaxcontra');

INSERT INTO `champions` (`id`, `name`, `role`, `difficulty`, `attack_damage`, `ability_power`, `life`, `mana`) VALUES
(1, 'Ashe', 'Asesino', 3, 100, 20, 50, 40),
(2, 'Ganondorf', 'Dale zelda dale', 3, 100, 20, 50, 40),
(3, 'Melendi', 'Marine', 2, 100, 2147483647, 2147483647, 2147483647);

INSERT INTO `modos` (`id`, `nombre`) VALUES
(1, 'Aram'),
(2, 'Clasificatorio'),
(3, 'Normal');

INSERT INTO `players` (`id`, `name`, `password_hash`, `registration_date`, `level`, `rank`, `bloqueado`) VALUES
(1, 'Faker', 'fakerpass', '2023-04-27', 34, 'rango', 0),
(2, 'David', '123', '2023-05-10', 4, 'Maestro', 0),
(3, 'Ramiro', 'fernandoRamiro', '2023-05-02', 34, 'rango', 0),
(4, 'Musculitos', 'miMadre', '2023-04-27', 34, 'rango', 0);

INSERT INTO `abilities` (`id`, `champion_id`, `name`, `description`) VALUES
(1, 2, 'Flecha de hielo', 'Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.'),
(2, 2, 'Ataque lodo', 'Una bomba de lodo inunda el campo de batalla'),
(3, 2, 'Enfoque cristalino', 'Ashe aumenta su daño crítico y su velocidad de ataque durante un corto período de tiempo.'),
(4, 2, 'Flecha de cristal encantada', 'Ashe dispara una flecha que atraviesa a los enemigos y los ralentiza.'),
(5, 2, 'Estrella ninja', 'Zed lanza una estrella ninja que inflige daño a los enemigos alcanzados.'),
(6, 2, 'Sombra viviente', 'Zed se desplaza hacia una sombra que ha creado previamente, infligiendo daño a los enemigos cercanos.'),
(7, 2, 'Cuchillada sombría', 'Zed ataca con sus cuchillas sombrías, infligiendo daño a los enemigos alcanzados.'),
(8, 2, 'Marca de la muerte', 'Zed marca a un enemigo, infligiendo daño adicional cuando lo ataca de nuevo después de un corto período de tiempo.'),
(9, 2, 'Golpe abrumador', 'Mordekaiser inflige daño físico a los enemigos cercanos y reduce su velocidad de movimiento.'),
(10, 2, 'Tormenta de metal', 'Mordekaiser inflige daño mágico a los enemigos cercanos y cura una cantidad de su vida. Además, crea un escudo que absorbe daño.'),
(11, 2, 'Reino de la muerte', 'Mordekaiser arrastra a un enemigo al reino de la muerte, lo que le permite luchar contra él en un duelo uno contra uno. Si Mordekaiser mata al enemigo, obtiene su alma y puede invocar a su espíritu para que luche a su lado durante un breve período de tiempo.'),
(12, 2, 'Poder de las sombras', 'Si Mordekaiser golpea a un enemigo con sus habilidades, obtiene un escudo que absorbe daño y aumenta su poder.');

INSERT INTO `matches` (`id`, `date`, `duration`, `result`, `Estadisticas`, `champion_id`, `modo_id`, `player_id`) VALUES
(1, '2023-05-05', 43, 0, '0/6/7', 1, 1, 3),
(2, '2023-05-10', 40, 1, '6/5/9', 2, 1, 2),
(3, '2023-05-10', 34, 1, '2/7/4', 1, 2, 1),
(4, '2023-05-10', 67, 0, '0,7,8', 2, 3, 1);