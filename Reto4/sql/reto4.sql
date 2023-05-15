-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2023 a las 23:52:51
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
  `champion_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `abilities`
--

INSERT INTO `abilities` (`id`, `champion_id`, `name`, `description`) VALUES
(1, 1, 'Flecha de hielo', 'Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.'),
(2, 1, 'Ataque lodo', 'Una bomba de lodo inunda el campo de batalla'),
(3, 1, 'Enfoque cristalino', 'Ashe aumenta su daño crítico y su velocidad de ataque durante un corto período de tiempo.'),
(4, 1, 'Flecha de cristal encantada', 'Ashe dispara una flecha que atraviesa a los enemigos y los ralentiza.'),
(5, 2, 'Estrella ninja', 'Zed lanza una estrella ninja que inflige daño a los enemigos alcanzados.'),
(6, 2, 'Sombra viviente', 'Zed se desplaza hacia una sombra que ha creado previamente, infligiendo daño a los enemigos cercanos.'),
(7, 2, 'Cuchillada sombría', 'Zed ataca con sus cuchillas sombrías, infligiendo daño a los enemigos alcanzados.'),
(8, 2, 'Marca de la muerte', 'Zed marca a un enemigo, infligiendo daño adicional cuando lo ataca de nuevo después de un corto período de tiempo.'),
(9, 3, 'Golpe abrumador', 'Mordekaiser inflige daño físico a los enemigos cercanos y reduce su velocidad de movimiento.'),
(10, 3, 'Tormenta de metal', 'Mordekaiser inflige daño mágico a los enemigos cercanos y cura una cantidad de su vida. Además, crea un escudo que absorbe daño.'),
(11, 3, 'Reino de la muerte', 'Mordekaiser arrastra a un enemigo al reino de la muerte, lo que le permite luchar contra él en un duelo uno contra uno. Si Mordekaiser mata al enemigo, obtiene su alma y puede invocar a su espíritu para que luche a su lado durante un breve período de tiempo.'),
(12, 3, 'Poder de las sombras', 'Si Mordekaiser golpea a un enemigo con sus habilidades, obtiene un escudo que absorbe daño y aumenta su poder.'),
(13, 4, 'Golpe Decisivo', 'Garen golpea el suelo con su espada, infligiendo daño a los enemigos cercanos y silenciándolos.'),
(14, 4, 'Juicio', 'Garen da vueltas con su espada, infligiendo daño a los enemigos cercanos durante varios segundos.'),
(15, 4, 'Valentía', 'Garen se fortalece, ganando un escudo y reduciendo el daño que recibe durante un corto período de tiempo.'),
(16, 4, 'Demacian Justice', 'Garen marca a un enemigo y después de unos segundos, inflige daño verdadero adicional y ejecuta al objetivo si su vida es suficientemente baja.'),
(17, 5, 'Hacha de Noxus', 'Darius inflige daño físico a los enemigos cercanos con su hacha.'),
(18, 5, 'Desgarrar', 'Darius inflige daño físico a un solo enemigo y lo ralentiza.'),
(19, 5, 'Hemorragia', 'Darius inflige daño físico y aplica una hemorragia que inflige daño adicional durante varios segundos.'),
(20, 5, 'Danza de la muerte', 'Darius se cura a sí mismo y gana velocidad de ataque y de movimiento durante varios segundos.'),
(21, 6, 'Estrella ninja', 'Zed lanza una estrella ninja que inflige daño a los enemigos alcanzados.'),
(22, 6, 'Sombra viviente', 'Zed se desplaza hacia una sombra que ha creado previamente, infligiendo daño a los enemigos cercanos.'),
(23, 6, 'Cuchillada sombría', 'Zed ataca con sus cuchillas sombrías, infligiendo daño a los enemigos alcanzados.'),
(24, 6, 'Marca de la muerte', 'Zed marca a un enemigo, infligiendo daño adicional cuando lo ataca de nuevo después de un corto período de tiempo.'),
(26, 7, 'Fuerza de la voluntad', 'Syndra levanta y arroja objetos cercanos, infligiendo daño a los enemigos alcanzados.'),
(27, 7, 'Dispersión de la debilidad', 'Syndra inflige daño mágico a los enemigos cercanos y los ralentiza durante un corto período de tiempo.'),
(28, 7, 'Unleashed Power', 'Syndra concentra todo su poder en una esfera oscura, infligiendo daño mágico a un solo enemigo.'),
(29, 6, 'To the Skies!', 'Jayce leaps to an enemy dealing physical damage to his target and to enemies in the area, slowing them for a short amount of time.'),
(30, 6, 'Lightning Field', 'Passive: Jayce restores mana for each strike in Hammer Stance.\nActive: Jayce creates an electric field, dealing magic damage to nearby enemies over time.'),
(31, 6, 'Thundering Blow', 'Jayce deals magic damage to an enemy and knocks them back a short distance.'),
(32, 6, 'Transform: Mercury Cannon', 'Jayce transforms into the Mercury Cannon, gaining ranged attacks and empowering his abilities.'),
(33, 6, 'To the Skies!', 'Jayce leaps to an enemy dealing physical damage to his target and to enemies in the area, slowing them for a short amount of time.'),
(34, 6, 'Lightning Field', 'Passive: Jayce restores mana for each strike in Hammer Stance.\nActive: Jayce creates an electric field, dealing magic damage to nearby enemies over time.'),
(35, 6, 'Thundering Blow', 'Jayce deals magic damage to an enemy and knocks them back a short distance.'),
(36, 6, 'Transform: Mercury Cannon', 'Jayce transforms into the Mercury Cannon, gaining ranged attacks and empowering his abilities.'),
(37, 8, 'Switcheroo!', 'Jinx swaps weapons: Pow-Pow, her minigun, deals increased attack speed, while Fishbones, her rocket launcher, deals damage to all enemies in an area.'),
(38, 8, 'Zap!', 'Jinx fires a shock blast that deals damage to the first enemy hit, revealing and slowing them.'),
(39, 8, 'Flame Chompers!', 'Jinx throws out a line of snare grenades that explode after a few seconds, dealing damage to enemies caught in the blast and rooting them in place.'),
(40, 8, 'Super Mega Death Rocket!', 'Jinx fires a rocket that gains damage as it travels across the map. It explodes on the first enemy champion hit, dealing damage to them and nearby enemies based on their missing health.'),
(41, 9, 'Thundering Smash', 'Volibear slams the ground, damaging and slowing all enemies caught in the effect.'),
(42, 9, 'Frenzied Maul', 'Volibear\'s next attack deals bonus physical damage and grants attack speed.'),
(43, 9, 'Majestic Roar', 'Volibear lets out a powerful roar, damaging and slowing all nearby enemies.'),
(44, 9, 'Stormbringer', 'Volibear summons a lightning bolt to strike a target location, dealing damage and stunning enemies caught in the area.'),
(45, 10, 'Solar Flare', 'Leona calls down a beam of solar energy, dealing magic damage to enemies in an area. Enemies in the center of the area are stunned, while enemies on the outer edge are slowed.'),
(46, 10, 'Sunlight ', 'Leonas damaging abilities mark enemies with Sunlight. When Leonas allies deal damage to marked enemies, they consume the mark to deal additional magic damage.'),
(47, 10, 'Radiance', 'Leona raises her shield, granting herself bonus Armor and Magic Resistance for a few seconds. She also calls down a beam of sunlight that deals magic damage to all nearby enemies and reveals them for a few seconds.'),
(48, 11, 'Disintegrate', 'Annie hurls a fireball at her target, dealing magic damage. If Disintegrate kills the target, its cooldown is refreshed.'),
(49, 11, 'Incinerate', 'Annie casts a cone of fire in front of her, dealing magic damage to all enemies in the area.'),
(50, 11, 'Molten Shield', 'Annie surrounds herself with a fiery shield, increasing her Armor and Magic Resistance and dealing magic damage to enemies who attack her with basic attacks.'),
(51, 12, 'Mystic Shot', 'Ezreal fires a bolt of energy in a line, dealing physical damage to the first enemy hit. If Mystic Shot successfully hits an enemy, Ezreals cooldowns are reduced.'),
(52, 12, 'Essence Flux', 'Ezreal fires an orb that passes through enemies, dealing magic damage to them. It then returns to Ezreal, healing him for each enemy champion hit.'),
(53, 12, 'Arcane Shift', 'Ezreal instantly teleports to a nearby target location and fires a homing bolt at the nearest enemy unit, dealing magic damage to it.'),
(54, 13, 'Bloodlust (Passive)', 'Tryndamere gains increased Attack Damage for each point of Fury he has.'),
(55, 13, 'Mocking Shout', 'Tryndamere lets out an intimidating shout, reducing the Attack Damage of nearby enemy champions and forcing them to focus their attacks on him for a few seconds.'),
(56, 13, 'Undying Rage', 'Tryndamere becomes completely immune to death for a few seconds, refusing to be reduced below a certain amount of Health.'),
(57, 10, 'Solar Flare (Ultimate)', 'Leona calls down a beam of solar energy, dealing magic damage to enemies in the target area and slowing them. Enemies in the center of the area are stunned instead of slowed.'),
(58, 11, 'Summon: Tibbers (Ultimate)', 'Annie summons Tibbers, her bear, to the target location. Tibbers deals magic damage to enemies in the area of effect and becomes a controllable unit that attacks Annie\'s enemies.'),
(59, 12, 'Trueshot Barrage (Ultimate)', 'Ezreal fires a powerful energy projectile across the map, damaging all enemies it passes through.'),
(60, 13, 'Undying Rage (Ultimate)', 'Tryndamere becomes completely immune to death for a few seconds, refusing to be reduced below a certain amount of Health and gaining bonus Fury.');

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
(1, 'David', 'davidcontra'),
(2, 'Gaizka', 'gaizkacontra'),
(3, 'Unax', 'unaxcontra');

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
(1, 'Ashe', 'Tirador', 2, 100, 20, 50, 40),
(2, 'Zed', 'Asesino', 3, 100, 20, 50, 40),
(3, 'Mordekaiser', 'Tanque', 2, 100, 0, 230, 50),
(4, 'Garen', 'Tanque', 1, 70, 0, 200, 0),
(5, 'Darius', 'Luchador', 2, 90, 0, 200, 100),
(6, 'Jayce', 'Asesino', 3, 100, 40, 150, 50),
(7, 'Syndra', 'Mago', 3, 0, 100, 120, 80),
(8, 'Jinx', 'Tirador', 2, 120, 20, 150, 0),
(9, 'Volibear', 'Luchador', 2, 90, 0, 200, 100),
(10, 'Leona', 'Soporte', 1, 0, 20, 180, 80),
(11, 'Annie', 'Mago', 1, 0, 100, 100, 50),
(12, 'Ezreal', 'Tirador', 3, 110, 50, 120, 80),
(13, 'Tryndamere', 'Luchador', 2, 100, 0, 200, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matches`
--

CREATE TABLE `matches` (
  `id` int(11) NOT NULL,
  `date` varchar(10) NOT NULL,
  `duration` int(11) NOT NULL,
  `result` tinyint(1) DEFAULT NULL,
  `Estadisticas` varchar(100) NOT NULL,
  `champion_id` int(11) NOT NULL,
  `modo_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matches`
--

INSERT INTO `matches` (`id`, `date`, `duration`, `result`, `Estadisticas`, `champion_id`, `modo_id`, `player_id`) VALUES
(1, '2023-05-05', 43, 0, '0/6/7', 1, 1, 3),
(2, '2023-05-10', 40, 1, '6/5/9', 2, 1, 2),
(196, '2023-05-15', 95, 1, '10/1/4', 1, 1, 5),
(197, '2023-05-15', 115, 1, '10/3/14', 1, 1, 5),
(198, '2023-05-15', 52, 1, '8/5/9', 1, 1, 5),
(199, '2023-05-15', 70, 0, '12/5/15', 1, 1, 5),
(200, '2023-05-15', 89, 1, '14/4/16', 1, 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modos`
--

CREATE TABLE `modos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modos`
--

INSERT INTO `modos` (`id`, `nombre`) VALUES
(1, 'Aram'),
(2, 'Clasificatoria'),
(3, 'Normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `players`
--

CREATE TABLE `players` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password_hash` varchar(100) NOT NULL,
  `registration_date` varchar(10) NOT NULL,
  `level` int(11) NOT NULL DEFAULT 1,
  `rank` varchar(15) NOT NULL DEFAULT 'Iron',
  `bloqueado` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `players`
--

INSERT INTO `players` (`id`, `name`, `password_hash`, `registration_date`, `level`, `rank`, `bloqueado`) VALUES
(2, 'David', '123', '2023-05-10', 4, 'Maestro', 0),
(3, 'Ramiro', 'fernandoRamiro', '2023-05-02', 34, 'rango', 0),
(4, 'Musculitos', 'miMadre', '2023-04-27', 34, 'rango', 0),
(5, 'Fake', '1234', '2023-05-15', 7, 'Iron', 0);

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
-- Indices de la tabla `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `champion_id` (`champion_id`),
  ADD KEY `modo_id` (`modo_id`),
  ADD KEY `player_id` (`player_id`);

--
-- Indices de la tabla `modos`
--
ALTER TABLE `modos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abilities`
--
ALTER TABLE `abilities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `champions`
--
ALTER TABLE `champions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `matches`
--
ALTER TABLE `matches`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `modos`
--
ALTER TABLE `modos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `players`
--
ALTER TABLE `players`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `abilities`
--
ALTER TABLE `abilities`
  ADD CONSTRAINT `abilities_ibfk_1` FOREIGN KEY (`champion_id`) REFERENCES `champions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`champion_id`) REFERENCES `champions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`modo_id`) REFERENCES `modos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matches_ibfk_3` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
