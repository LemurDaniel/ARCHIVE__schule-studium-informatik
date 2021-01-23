create table e.Haus(
	hnr int primary key,
	adresse nvarchar(50),
)
create table e.Interessent(
	id int primary key,
	iname nvarchar(50),
)
create table e.Wohnung(
	hnr int references e.Haus,
	wnr int,
	qm int,
	zuschlag int references e.Interessent,
	zTyp nvarchar(5) check(zTyp='Miete' or zTyp='Kauf'),
	primary key(hnr, wnr),
)
create table e.Bewerbung(
	hnr int,
	wnr int,
	interressent_id int references e.Interessent,
	bTyp nvarchar(5) check(bTyp='Miete' or bTyp='Kauf'),
	primary key(hnr, wnr, interressent_id, bTyp),
	foreign key(hnr, wnr) references e.Wohnung
)
