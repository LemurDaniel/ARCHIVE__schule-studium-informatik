--  9.	Wie viele Mitarbeiter haben ein Notebook.

select count(distinct h.Personalnummer) [Anzahl der Mitarbeiter mit Notebook]
                                  -- count(*) nicht m�glich da ein Mitarbeiter mehrere Notebooks haben k�nnte
from Mitarbeiter m join Hardware h on m.Personalnummer=h.Personalnummer
where Ger�tetyp = 'Notebook'

-- Variante 1
select count(*) from Mitarbeiter
where Personalnummer in (select Personalnummer from Hardware where Ger�tetyp = 'Notebook')

-- Variante 2
select count(*) from Mitarbeiter m
where exists(select * from Hardware h where h.Ger�tetyp='Notebook' and h.Personalnummer=m.Personalnummer)