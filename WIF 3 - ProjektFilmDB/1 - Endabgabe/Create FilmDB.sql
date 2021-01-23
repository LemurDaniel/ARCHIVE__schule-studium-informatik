
create table rechte(
	id int primary key,
	berechtigung nvarchar(20) not null,
	[read] bit not null,
	[add] bit not null,
	[update] bit not null,
	updateAll bit not null,
	multilogin bit not null,
	reviewRead bit not null,
	reviewWrite bit not null,
	reviewWriteAll bit not null,
	list bit not null,
)

create table nutzer(
	id int primary key identity(0, 1),
	name nvarchar(30) unique not null,
	passwort nvarchar(70) not null,
	rechte int references rechte not null,
)

create table angemeldet(
	nid int primary key references nutzer,
	iid int unique not null,
)

create table film(
	id int primary key identity(0,1),
	titel nvarchar(50) unique not null,
	dauer int not null,
	erscheinungsjahr int not null,
	ersteller int references nutzer,
	bewertung decimal(3,1) not null,
)

create table rezension(
	verfasser int references nutzer,
	filmid int references film,
	bewertung int check(bewertung<=10 and bewertung >=0),
	titel nvarchar(30) not null,
	inhalt nvarchar(500) not null,
)

create table person(
	id int primary key identity(0, 1),
	vorname nvarchar(50) not null,
	[name] nvarchar(50) not null,
	unique(vorname, [name]),
)

create table rolle(
	id int primary key,
	rolle nvarchar(30) unique not null,
)

create table film_person_rolle(
	fid int references film,
	pid int references person,
	rid int references rolle,
	weiteres nvarchar(20),
	primary key(fid, pid, rid),
)

create table genre(
	id int primary key,
	genre nvarchar(20) unique not null,
	text nvarchar(500) not null,
)

create table genre_film(
	gid int references genre,
	fid int references film,
	primary key(fid, gid),
)

create table liste(
	id int primary key identity(0,1),
	besitzer int references nutzer,
	[name] nvarchar(50) not null,
	unique(besitzer, [name]),
)

create table liste_film(
	lid int references liste,
	fid int references film,
	primary key(fid, lid),
)


insert into genre (id, genre, text)
values
(0, 'Abenteuer', 'Der Film handelt von einem oder mehreren Protagonisten, welche gefährliche, aufregende und abenteuerliche Situation durchstehen um ein Ziel zu erreichen.'),
(1, 'Action', 'Filme, die zum überwiegenden Teil aus spektakulären actionreichen und oftmals destruktiven Szenen bestehen.'),
(2, 'Animation', 'Der Film handelt von einem oder mehreren Protagonisten, welche gefährliche, aufregende und abenteuerliche Situation durchstehen um ein Ziel zu erreichen.'),
(3, 'Biographie', 'Der Primäre Fokus liegt auf der Darstellung der Aktivitäten, Persönlichkeit und des Lebensweges einer realen Person in Form einer Dokumentation.'+char(13)+'Wenn der Fokus vorwiegen auf den Ereignissen anstatt der Person liegt sollte das Genre Geschichte bevorzugt werden.'),
(4, 'Doku', 'Enthält hauptsächlich Szenen von realen Personen ggf. Augenzeugen, welche bestimmte Ereignisse nacherzählen.'),
(5, 'Drama', 'Die Charaktere durchlaufen eine ernste Handlung bzw. Narration.'),
(6, 'Erwachsene', 'Filme, welche explizit sexuelle Handlungen oder Fetische darstellen.'),
(7, 'Familie', 'Filme geeignet für alle Altersklassen, hauptsächlich zur Unterhaltung.'),
(8, 'Fantasie', 'Enthält Fantasieelement wie Magie und Zauberei und spielt ggf. in einer Fantasiewelt.'),
(9, 'Film Noir', 'Enthält typischerweise dunkle und grübelnde Charaktere, Korruption und dunkle Abgründe einer Stadt. Vorwiegend in Schwarz weiß gefilmt.'),
(10, 'Geschichte', 'Das Hauptaugenmerk liegt auf der Darstellung und Nacherzählung von realen und signifikaten historischen Ereignissen. Wenn der Fokus hauptsächlich auf einer Person liegt sollte das Genre Biographie bevorzuget werden.'),
(11, 'Horror', 'Sollte vorwiegend Szenen enthalten in welchen Charaktere in furchterregenden und angsteinflößende Situationen geraden. Nicht zu verwechseln mit Thrillern.'),
(12, 'Komödie', 'Der Großteil aller Szenen beinhaltet Lustige oder erheiternde Situationen.'),
(13, 'Krieg', 'Der Film handelt hauptsächlich über Krieg.'),
(14, 'Krimi', 'Handelt von Protagonisten oder Antagonisten und derren Teilnahme an kriminellen Aktivitäten oder dessen Aufklärung.'),
(15, 'Kurzfilm', 'Ein Kinofilm mit geringerer Laufzeit als 44 Minuten.'),
(16, 'Liebe', 'Handelt von Charaktere und ihr persönliches Leben und Liebesbeziehungen mit anderen Charakteren.'),
(17, 'Musical', 'Musik und Gesang, oftmals begleitet von Tanzeinlagen, sind fester Bestandteil der Handlung.'),
(18, 'Musik', 'Enthält einen signifikanten teil Musik bezogener Elemente, obwohl es kein Musical ist. Ein Konzert oder eine Story über eine Band.'),
(19, 'Mystery', 'Thematiken, die geheimnisvolle, schaurige Darstellungen von mysteriösen, meist nicht mit natürlichen Phänomenen erklärbaren Verbrechen betreffen.'),
(20, 'Sci-Fi', 'Der Film Handlung basiert hauptsächlichen auf spekulativen wissenschaftlichen Entdeckungen, Entwicklungen und oftmals dem Weltraum.'),
(21, 'Sport', 'Der Fokus liegt auf einem Sport oder Sportevent, welches real oder fiktional sein kann.'),
(22, 'Thriller', 'Film der Spannung und Nervenkitzel erzeugt. Sollte nicht mit Horror verwechselt werden.'),
(23, 'Western', 'Spielt in der Zeitperiode der Erschließung des Amerikanischen Westens.');

insert into rolle(id, rolle)
values(0, 'Schausspieler'),
(1, 'Regisseur'),
(2, 'Drehbuchautor'),
(3, 'Komponist'),
(4, 'Editor'),
(5, 'Stunts'),
(6,'VFX Abteilung'),
(7,'SFX Abteilung'),
(8,'Ton Abteilung'),
(9,'Casting Abteilung'),
(10,'Makeup Abteilung'),
(11,'Kunst Abteilung'),
(12,'Musik Abteilung'),
(13,'Kostüm Design'),
(14,'Set Design'),
(15,'Sonstige'),
(16,'Besonderer Dank');

insert into rechte
values(0, 'Gast', 1, 0, 0, 0, 1, 0, 0, 0, 0),
(1, 'Benutzer', 1, 1, 1, 0, 0, 1, 1, 0, 1),
(90, 'Daniel_Testing', 1, 1, 1, 0, 0, 1, 1, 0, 1),
(100, 'Alle Rechte', 1, 1, 1, 1, 1, 1, 1, 1, 1);

insert into nutzer(name, passwort, rechte)
values('Gast', '$2a$10$hvpF..3XzE7U/WioszCDZ.zad5fDtrKFWqjBZ1QfgDhpS0zwCw6Sm', 0),
('Daniel', '$2a$10$JskgFXC/EYjxReFCtNaby..tpKZeKldVjw519XbwSBFCXxHt3HEim', 90),
('Alle Rechte', '$2a$10$HbP4Ujaf9QzPR3SzYYWiv.zC6H9u2P3/JEfvbhGB28Lg.PCjZkHAi', 100);


Go

create procedure updateFilmBewertung @FilmId int
As
Update film set bewertung = Isnull((Select AVG( CAST(bewertung AS decimal(3,1)) ) from rezension where filmid = @FilmId), 0)
where film.id = @FilmId;


Go

CREATE TRIGGER dbo.updateFilmbewertungTrigger 
ON rezension  
AFTER Insert, update, delete
AS
declare @id int;
set @id =(Case
			when Exists (select filmid from inserted) then (select filmid from inserted)
			else (select filmid from deleted)
			End
			);
exec updateFilmBewertung @FilmId = @id;
			
Go

CREATE TRIGGER dbo.createListen 
ON nutzer  
AFTER Insert
AS
declare @id int
set @id = (select id from inserted)
Insert into liste(besitzer, name)
values(@id, 'Favoriten'),
(@id, 'Watchlist');

			
