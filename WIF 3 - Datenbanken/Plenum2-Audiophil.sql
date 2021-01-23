create table g.Besitzer(
	mnr int primary key,
	bname nvarchar(50)
)

create table g.CD(
	id int primary key,
	k�nstler nvarchar(50),
	besitzer int references g.besitzer not null
)

create table g.Eigent�mer (
	mnr int primary key references g.besitzer,
	genre nvarchar(50)
)

create table g.Titel(
	id int references g.CD,
	titel nvarchar(50),
	primary key(id, titel),
)

create table g.Besitz(
	mnr int references g.Eigent�mer,
	id int references g.CD,
	primary key(mnr, id)
)

