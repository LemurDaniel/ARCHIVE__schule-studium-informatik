use	DB0_Landau
create table Artikel(
	id int primary key,
	bezeichnung nvarchar(50) not null,
	menge int check(menge > 0) default 5,
	preis money check(preis > 0),
	mindestbestand int default 5,
	check(menge >= mindestbestand)  
)

GO

create table Fremdartikel(
	id int primary key references Artikel,
	firma nvarchar(50)
)

GO

alter table Fremdartikel add lagerort nvarchar(50) 