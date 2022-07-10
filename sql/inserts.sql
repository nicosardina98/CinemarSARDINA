INSERT INTO cinemar2.clasificación VALUES
(1,"Todas las edades pueden ver. No hay desnudez ni sangre y/o alcohol. El lenguaje es cortés sin el uso de insultos o con ofensas muy suaves que caen en lo gracioso.",'ATP'),
(2,"Apta para mayores de 13 años. Desnudez parcial, sangre leve, muertes poco violentas, lenguaje regularizado e imágenes intensas suelen aparecer en las películas de esta clasificación. Pueden ingresar menores si van acompañados por un familiar o tutor.",'+13'),
(3,"Apta para mayores de 16 años. Desnudez fuerte y explícita —pero no pornográfica—, escenas fuertes, alcohol y drogas, insultos, imágenes muy intensas, muertes muy violentas y sangre en mucha cantidad —gore—. Se recomienda discreción para los menores de 16 años.",'+16'),
(4,"Apta para mayores de edad. Los menores de edad no están destinados a ver la película. Desnudez fuerte —pornografía—, violencia extrema, muertes extremadamente violentas, lenguaje ofensivo, derramamiento de sangre —gore extremo—, imágenes intensas frecuentes, escenas intensamente fuertes, insultos intensos y alcohol, drogas y tabaco",'+18');


INSERT INTO cinemar2.usuario VALUES
(1,'Nicolás', 'Sardina', '1998-01-25','3886415860','nicosardina98@gmail.com','Cliente'),
(2,'Carla', 'Senzano', '2002-05-20','3886452993','carlasenzano02@gmail.com','Cliente'),
(3,'Marina', 'Miranda', '1980-09-30','3886419372','marinamiranda80@gmail.com','Administrador'),
(4,'Hector', 'Salas', '1998-07-14','3886562767','lobebe98@gmail.com','Cliente'),
(5,'Cristian', 'Reales', '1996-04-10','3886501049','meme96@gmail.com','Cliente'),
(6,'Leandro', 'Reales', '1997-11-05','3874661535','leanreales97@gmail.com','Cliente'),
(7,'Agustina', 'Miranda', '2003-05-21','3886564326','agusmiranda@gmail.com','Cliente'),
(8,'José', 'Osorio', '1990-12-01','3886635478','joseluis90@gmail.com','Administrador'),
(9,'Cardozo', 'Mauricio', '2000-03-08','3886652130','mauricardozo00@gmail.com','Cliente'),
(10,'Miranda', 'Maki', '1999-04-14','3886603778','makimiranda99@gmail.com','Cliente');

INSERT INTO cinemar2.cliente VALUES
(1,'nicocha1998', 'JuanRoman10!', '2021-01-19',1),
(2,'carlita2022', 'Cinemar02!', '2021-01-19',2),
(3,'lobebe1998', 'BebeloConSoda23!', '2022-06-19',4),
(4,'meme1996', 'Cinemar04!', '2018-10-05',5),
(5,'leandro1997', 'LeandroBoca12!', '2018-02-14',6),
(6,'agustina2003', 'SofiaMiranda21!', '2021-05-01',7),
(7,'mauricio2001', 'AxelFortunato08!', '2018-07-10',9),
(8,'maki1999', 'SopaDoMacaco05!', '2020-04-14',10);

INSERT INTO cinemar2.administrador VALUES
(1,'MarinaAdmin01', 'admin01!', 10,3),
(2,'JoseAdmin02', 'admin02!', 6,8);

INSERT INTO cinemar2.película VALUES
(1,'Limitless', 105 , 'Suspenso', 'Español', '2'),
(2,'Garra', 117 , 'Deporte/Comedia', 'Español', '3'),
(3,'Lightyear',105  , 'Aventura/Comedia', 'Español', '1'),
(4,'21 Blackjack', 123 , 'Drama/Crimen', 'Español', '3'),
(5,'Dr Strange en el multiverso de la locura', 126 , 'Superheroes', 'Inglés', '2');

INSERT INTO cinemar2.sala VALUES
('L1',1,'2D', 80,1),
('L2',2,'3D', 40,2),
('L3',3,'2D', 80,3),
('L4',4,'3D', 40,4);

INSERT INTO cinemar2.butaca VALUES
('1A1','A', 1, 1,'si'), ('1A2','A', 2, 1,'no'), ('1A3','A', 3, 1,'no'),  ('1A4','A', 4, 1,'no'), ('1A5','A', 5, 1,'no'), 
('2A1','A', 1, 2,'no'), ('2A2','A', 2, 2,'no'), ('2A3','A', 3, 2,'no'),  ('2A4','A', 4, 2,'no'), ('2A5','A', 5, 2,'no'), 
('3A1','A', 1, 3,'no'),('3A2','A', 2, 3,'no'), ('3A3','A', 3, 3,'no'),  ('3A4','A', 4, 3,'no'), ('3A5','A', 5, 3,'no'), 
('4A1','A', 1, 4,'no'),('4A2','A', 2, 4,'no'), ('4A3','A', 3, 4,'no'),  ('4A4','A', 4, 4,'no'), ('4A5','A', 5, 4,'no'); 


INSERT INTO cinemar2.descuento VALUES
(1,'Lunes',0.2),
(2,'Martes',0.15),
(3,'Miercoles',0.2),
(4,'Jueves',0.15),
(5,'Viernes',0.1),
(6,'Sabado',0.1),
(7,'Domingo',0.1);

INSERT INTO cinemar2.tarjeta VALUES
(14867235,'2024-01-19','nicocha1998');

INSERT INTO cinemar2.sesión VALUES
(1,'19:00',500.00, 'Lunes', 1,'Lunes');

INSERT INTO cinemar2.reserva VALUES
(1,'L1A117','nicocha1998', '1A1');

