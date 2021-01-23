create table A2.Lieferant(
	lnr nvarchar(10) primary key,
	lname nvarchar(40) not null,
	sitz nvarchar(40)	
);

create table A2.Projekt(
	pnr nvarchar(10) primary key,
	pname nvarchar(40) not null,
	ort nvarchar(40),
);

create table A2.Teil(
	tnr nvarchar(10) primary key,
	tname nvarchar(40) not null,
	farbe nvarchar(40),
	gewicht int not null check(gewicht>0),
	preis money,
)

create table A2.LTP(
	lnr nvarchar(10) references A2.Lieferant,
	tnr nvarchar(10) references A2.Teil,
	pnr nvarchar(10) references A2.Projekt,
	menge int not null check(menge>0)
);

-- 2b
GO

Alter table A2.Lieferant
Add [status] int

-- 2c
GO

Alter table A2.Teil
Alter column preis decimal(16,2)

-- 2d
GO

Alter table A2.Teil
Drop column preis


-- 2c
GO

Drop table A2.LTP;
Drop table A2.Lieferant;
Drop table A2.Projekt;
Drop table A2.Teil;