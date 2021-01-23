--  22.	Bestimmen Sie die Mitarbeiter, die sich mehr als 5-mal Firmenfahrzeuge ausgeliehen haben.
--      Berechnen Sie die Summe der Verleih-Tage für diese Mitarbeiter:
--      Personalnummer, Nachname, Anzahl der Ausleihen, Summe der Verleihtage.

select m.Personalnummer, m.Nachname, count(m.Personalnummer) as [Anzahl der Ausleihen], sum(day(Datumbis-Datumvon)) as [Summe der Verleihtage]
from Mitarbeiter m join Fahrzeugausleihe fa on m.Personalnummer=fa.Personalnummer
group by m.Personalnummer, m.Nachname
having count(m.Personalnummer)>5