-- 30.	Definieren Sie eine Tabelle PERSONALTABELLE(Mnr, Name, Gehalt, Chef).
--      Mnr ist der Prim�rschl�ssel, Chef ein Fremdschl�ssel auf Mnr.
--      
--   folgende SQL-Sequenz geht nur einmal - au�er die Tabelle PERSONALTABELLE
--   wird wieder gel�scht


--Tabelle erzeugen
create table PERSONALTABELLE(
	mnr int identity primary key,
	Mname nvarchar(50),
	Gehalt money,
	Chef int foreign key references PERSONALTABELLE
)


-- Tragen Sie die folgende Werte in die Tabelle ein:
-- �	1, �Schmitt�, 50000, NULL
-- �	2, �Huber�, 40000, 1
-- �	3, �Lehmann�, 45000, 2
-- �	4, �M�ller�, 35000, 2
-- �	5, �Maier�, 30000, 1
--Dateneingabe
insert into PERSONALTABELLE (Mname, Gehalt, Chef)
values ('Schmitt', 50000, NULL),
	   ('Huber', 40000, 1),
	   ('Lehmann', 45000, 2),
	   ('M�ller', 35000, 2),
	   ('Maier', 30000, 1)


-- Formulieren Sie die Anfrage(�Managerfrage�): Wer verdient mehr als sein Chef???
-- Hinweis: das Attribut �Chef� (Fremdschl�ssel) ist eine Referenz auf das Attribut �Mnr� (Prim�rschl�ssel).
--Abfrage
select angestellt.Mname as Mitarbeiter, angestellt.Gehalt, chef.Mname as Chef, chef.Gehalt
from PERSONALTABELLE angestellt join PERSONALTABELLE chef on
	angestellt.Chef = chef.mnr
where angestellt.Gehalt>chef.Gehalt