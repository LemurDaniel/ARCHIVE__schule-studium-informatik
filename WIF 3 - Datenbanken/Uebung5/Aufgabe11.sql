--   11.	Erstellen Sie eine Liste aller Mitarbeiter mit Notebooks sortiert nach den Herstellern des Notebooks)

select m.Personalnummer, Nachname, Hersteller
from Mitarbeiter m join Hardware h on m.Personalnummer=h.Personalnummer
where Gerätetyp = 'Notebook'
order by Hersteller