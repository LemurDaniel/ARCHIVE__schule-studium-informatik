create table g.Besitzer(
	mnr int primary key,
	bname nvarchar(50)
)

create table g.CD(
	id int primary key,
	künstler nvarchar(50),
	besitzer int references g.besitzer not null
)

create table g.Eigentümer (
	mnr int primary key references g.besitzer,
	genre nvarchar(50)
)

create table g.Titel(
	id int references g.CD,
	titel nvarchar(50),
	primary key(id, titel),
)

create table g.Besitz(
	mnr int references g.Eigentümer,
	id int references g.CD,
	primary key(mnr, id)
)

