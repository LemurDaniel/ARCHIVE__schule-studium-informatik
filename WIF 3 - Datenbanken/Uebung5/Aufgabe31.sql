--31.	Erstellen Sie eine Liste der Mitarbeiter (Personalnummer, Nachname), die sich genau 4-mal ein Fahrzeug ausgeliehen haben.
--      Erstellen Sie dazu zwei Varianten:
--      •	mit join, group by und having
--      •	Korrelationsvariablen in Unterabfrage

select m.Personalnummer, Nachname
from Mitarbeiter m join Fahrzeugausleihe fa on m.Personalnummer=fa.Personalnummer
group by m.Personalnummer, Nachname
having count(*) = 4

select Personalnummer, Nachname
from Mitarbeiter m
where (select count(*) from Fahrzeugausleihe fa where fa.Personalnummer=m.Personalnummer) = 4
