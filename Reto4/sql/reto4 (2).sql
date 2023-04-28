-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-04-2023 a las 10:11:27
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reto4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abilities`
--

CREATE TABLE `abilities` (
  `id` int(11) NOT NULL,
  `champion_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `abilities`
--

INSERT INTO `abilities` (`id`, `champion_id`, `name`, `description`) VALUES
(1, 1, 'Flecha de hielo', 'Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.'),
(2, 1, 'Descarga de escarcha', 'Ashe lanza una onda de frío que daña a los enemigos cercanos y ralentiza su velocidad de ataque.'),
(3, 1, 'Enfoque cristalino', 'Ashe aumenta su daño crítico y su velocidad de ataque durante un corto período de tiempo.'),
(4, 1, 'Flecha de cristal encantada', 'Ashe dispara una flecha que atraviesa a los enemigos y los ralentiza.'),
(5, 2, 'Estrella ninja', 'Zed lanza una estrella ninja que inflige daño a los enemigos alcanzados.'),
(6, 2, 'Sombra viviente', 'Zed se desplaza hacia una sombra que ha creado previamente, infligiendo daño a los enemigos cercanos.'),
(7, 2, 'Cuchillada sombría', 'Zed ataca con sus cuchillas sombrías, infligiendo daño a los enemigos alcanzados.'),
(8, 2, 'Marca de la muerte', 'Zed marca a un enemigo, infligiendo daño adicional cuando lo ataca de nuevo después de un corto período de tiempo.'),
(9, 4, 'Golpe abrumador', 'Mordekaiser inflige daño físico a los enemigos cercanos y reduce su velocidad de movimiento.'),
(10, 4, 'Tormenta de metal', 'Mordekaiser inflige daño mágico a los enemigos cercanos y cura una cantidad de su vida. Además, crea un escudo que absorbe daño.'),
(11, 4, 'Reino de la muerte', 'Mordekaiser arrastra a un enemigo al reino de la muerte, lo que le permite luchar contra él en un duelo uno contra uno. Si Mordekaiser mata al enemigo, obtiene su alma y puede invocar a su espíritu para que luche a su lado durante un breve período de tiempo.'),
(12, 4, 'Poder de las sombras', 'Si Mordekaiser golpea a un enemigo con sus habilidades, obtiene un escudo que absorbe daño y aumenta su poder.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admins`
--

INSERT INTO `admins` (`id`, `name`, `password`) VALUES
(100, 'gaizka', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `champions`
--

CREATE TABLE `champions` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `ability_power` int(11) DEFAULT NULL,
  `life` int(11) DEFAULT NULL,
  `mana` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `champions`
--

INSERT INTO `champions` (`id`, `name`, `role`, `difficulty`, `attack_damage`, `ability_power`, `life`, `mana`) VALUES
(1, 'Ashe', 'tirador', 1, 59, 0, 539, 280),
(2, 'Zed', 'asesino', 3, 63, 0, 584, 200),
(3, 'Ezreal', 'tirador', 3, 60, 90, 400, 250),
(4, 'Mordekaiser', 'luchador', 2, 75, 40, 650, 200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadisticas`
--

CREATE TABLE `estadisticas` (
  `cod` int(11) NOT NULL,
  `cod_partida` int(11) NOT NULL,
  `kills` int(11) NOT NULL,
  `assists` int(11) NOT NULL,
  `death` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `habilidades`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `habilidades` (
`Nombre` varchar(50)
,`habilidad` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matches`
--

CREATE TABLE `matches` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `result` tinyint(1) DEFAULT NULL,
  `champion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matches`
--

INSERT INTO `matches` (`id`, `date`, `duration`, `result`, `champion`) VALUES
(3, '2022-01-05 00:00:00', 40, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `players`
--

CREATE TABLE `players` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password_hash` varchar(100) NOT NULL,
  `registration_date` date NOT NULL,
  `level` int(11) NOT NULL,
  `rank` varchar(10) NOT NULL,
  `bloqueado` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `players`
--

INSERT INTO `players` (`id`, `name`, `password_hash`, `registration_date`, `level`, `rank`, `bloqueado`) VALUES
(1, 'johndoe', 'password123', '2022-01-01', 10, 'Silver', NULL),
(2, 'janesmith', 'secret456', '2022-01-02', 15, 'Gold', NULL),
(3, 'bobjohnson', 'qwerty789', '2022-01-03', 20, 'Platinum', NULL),
(4, 'Faker', 'fakerpass', '2022-01-04', 25, 'Diamond', NULL);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `recomendados`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `recomendados` (
`Dificultad` int(11)
,`Nombre` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `resumenpartidas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `resumenpartidas` (
`Jugador` varchar(50)
,`Fecha` datetime
,`Resultado` tinyint(1)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `habilidades`
--
DROP TABLE IF EXISTS `habilidades`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `habilidades`  AS SELECT `champions`.`name` AS `Nombre`, `abilities`.`name` AS `habilidad` FROM (`champions` join `abilities` on(`champions`.`id` = `abilities`.`champion_id`)) ORDER BY `champions`.`id` ASC ;

-- --------------------------------------------------------

--
-- Estructura para la vista `recomendados`
--
DROP TABLE IF EXISTS `recomendados`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recomendados`  AS SELECT `champions`.`difficulty` AS `Dificultad`, `champions`.`name` AS `Nombre` FROM `champions` ORDER BY `champions`.`difficulty` ASC ;

-- --------------------------------------------------------

--
-- Estructura para la vista `resumenpartidas`
--
DROP TABLE IF EXISTS `resumenpartidas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `resumenpartidas`  AS SELECT `players`.`name` AS `Jugador`, `matches`.`date` AS `Fecha`, `matches`.`result` AS `Resultado` FROM (`players` join `matches` on(`matches`.`id` = `players`.`id`)) ORDER BY `players`.`name` ASC, `matches`.`date` ASC ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abilities`
--
ALTER TABLE `abilities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `champion_id` (`champion_id`);

--
-- Indices de la tabla `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `champions`
--
ALTER TABLE `champions`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estadisticas`
--
ALTER TABLE `estadisticas`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `champion` (`champion`);

--
-- Indices de la tabla `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_players_name` (`name`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `abilities`
--
ALTER TABLE `abilities`
  ADD CONSTRAINT `abilities_ibfk_1` FOREIGN KEY (`champion_id`) REFERENCES `champions` (`id`);

--
-- Filtros para la tabla `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`champion`) REFERENCES `champions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
