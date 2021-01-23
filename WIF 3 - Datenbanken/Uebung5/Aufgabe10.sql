--  10.	Erstellen Sie eine Liste der Wohnorte von Mitarbeitern mit der Anzahl der Mitarbeiter, die dort wohnen.

select Ort, Count(*) as [Anzahl der Mitarbeiter]
from Mitarbeiter
group by Ort