--  19.	Erstellen Sie eine Liste der Mitarbeiter (Personalnummer, Nachname, Vorname),
--      die sich bisher noch kein Fahrzeug ausgeliehen haben. Verwenden Sie einen Outer Join.

select m.Personalnummer, Nachname, Vorname
from Mitarbeiter m left join Fahrzeugausleihe f on m.Personalnummer=f.Personalnummer
where f.Personalnummer is null