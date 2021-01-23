--  20.	Erstellen Sie eine Liste der Firmenfahrzeuge (Kennzeichen, Marke) die noch nie ausgeliehen wurden.
--      Verwenden Sie den EXISTS-Operator, generieren Sie die gleiche Abfrage mit dem IN-Operator.

-- exists Variante
select Kennzeichen, Marke
from Firmenfahrzeuge ff
where not exists(select * from Fahrzeugausleihe fa where ff.Kennzeichen=fa.Kennzeichen)

-- IN Variante
select Kennzeichen, Marke
from Firmenfahrzeuge
where Kennzeichen not IN (select Kennzeichen from Fahrzeugausleihe)