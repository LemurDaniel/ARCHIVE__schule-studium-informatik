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
('1', 'Leo', 'Bergstra�e 34'),
('2', 'Huber', 'Residenzstra�e 11'),
('3', 'Maier', 'H�henweg 18');

insert into A1.Lieferung
values
('1', 'Schutzbrille', '35.40'),
('1', 'Ru�filter', '62'),
('2', 'D�bel', '33.1'),
('2', 'N�gel', '16.8'),
('2', 'Schrauben', '78.3'),
('3', 'Spanplatten', '112');


select A1.Lieferant.Lieferant, Ware, Preis
from A1.Lieferung
inner join A1.Lieferant on A1.Lieferung.Lieferant=A1.Lieferant.ID;