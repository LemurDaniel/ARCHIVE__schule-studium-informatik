create table A2.Lieferservice(
	Name nvarchar(50) primary key,
	Ort nvarchar(50) not null,
	Straße nvarchar(50) not null
)

insert A2.Lieferservice
values
('Bella Italia', 'Ansbach', 'Weender Str. 8'),
('Casino Grande', 'Nürnberg', 'Am Weinberg 14'),
('Da Mafia', 'Ansbach', 'Lotzestraße 16-18'),
('Venezia', 'Nürnberg', 'Königsstraße 111');


create table A2.Preisliste(
	Produkt nvarchar(50) primary key,
	Preis money not null check(Preis>0)
)

insert A2.Preisliste
values
('Pizza', '5'),
('Lasagne', '6'),
('Gnochi', '4,5'),
('Salat', '3');


create table A2.Liefervertrag(
	Pizzeria nvarchar(50) references A2.Lieferservice,
	Produkt nvarchar(50) references A2.Preisliste,
	Anzahl int not null check(Anzahl>0),
	primary key(Pizzeria, Produkt)
)

insert A2.Liefervertrag
values
('Bella Italia', 'Pizza', '10'),
('Bella Italia', 'Lasagne', '15'),
('Bella Italia', 'Salat', '20'),
('Casino Grande', 'Pizza', '12'),
('Casino Grande', 'Salat', '15'),
('De Mafia', 'Gnochi', '60'),
('Venezia', 'Pizza', '20');


select A2.Liefervertrag.*, Preis As 'Preis/Stück', Preis*Anzahl As 'Kosten'
from A2.Liefervertrag
inner join A2.Preisliste on A2.Preisliste.Produkt=A2.Liefervertrag.Produkt