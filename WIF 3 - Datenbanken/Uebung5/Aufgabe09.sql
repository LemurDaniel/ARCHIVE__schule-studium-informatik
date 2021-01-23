--  9.	Wie viele Mitarbeiter haben ein Notebook.

select count(distinct h.Personalnummer) [Anzahl der Mitarbeiter mit Notebook]
                                  -- count(*) nicht möglich da ein Mitarbeiter mehrere Notebooks haben könnte
from Mitarbeiter m join Hardware h on m.Personalnummer=h.Personalnummer
where Gerätetyp = 'Notebook'

-- Variante 1
select count(*) from Mitarbeiter
where Personalnummer in (select Personalnummer from Hardware where Gerätetyp = 'Notebook')

-- Variante 2
select count(*) from Mitarbeiter m
where exists(select * from Hardware h where h.Gerätetyp='Notebook' and h.Personalnummer=m.Personalnummer)