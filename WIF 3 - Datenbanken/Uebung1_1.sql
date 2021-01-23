create schema A1 authorization db_accessadmin;

create table A1.Lieferant(
	ID int primary key,
	Lieferant nvarchar(50) not null,
	Adresse nvarchar(50) not null,
)

create table A1.Lieferung(
	Lieferant int references A1.Lieferant,
	Ware nvarchar(50) not null,
	Preis money not null check(Preis > 0),
)


insert into A1.Lieferant
values 
('1', 'Leo', 'Bergstraße 34'),
('2', 'Huber', 'Residenzstraße 11'),
('3', 'Maier', 'Höhenweg 18');

insert into A1.Lieferung
values
('1', 'Schutzbrille', '35.40'),
('1', 'Rußfilter', '62'),
('2', 'Dübel', '33.1'),
('2', 'Nägel', '16.8'),
('2', 'Schrauben', '78.3'),
('3', 'Spanplatten', '112');


select A1.Lieferant.Lieferant, Ware, Preis
from A1.Lieferung
inner join A1.Lieferant on A1.Lieferung.Lieferant=A1.Lieferant.ID;