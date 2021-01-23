--  6.	Erstellen Sie eine Liste der Mitarbeiter (Personalnummer, Nachname, Vorname, Titel).
--      Die Liste soll nach Namen sortiert sein, wobei in der Liste zuerst die Mitarbeiterinnen
--      und dann die Mitarbeiter angezeigt werden sollen.

select Personalnummer, Nachname, Vorname, Titel
from Mitarbeiter
order by Geschlecht    -- 1 ist weiblich, 2 ist männlich