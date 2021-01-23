--  27.	Erstellen Sie eine Liste aller Mitarbeiter (Personalnummer, Nachname, Ort) mit Wohnorten,
--      an denen es keinen Seminaranbieter gibt. Verwenden Sie dazu den ALL-Operator.
--      Generieren Sie die gleiche Abfrage mit dem ANY-Operator.

select Personalnummer, Nachname, Ort
from Mitarbeiter
where Ort <>all (select Ort from Seminaranbieter)


select Personalnummer, Nachname, Ort
from Mitarbeiter
where not Ort =any (select Ort from Seminaranbieter)