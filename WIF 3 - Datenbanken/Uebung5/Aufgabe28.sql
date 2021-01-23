--  28.	Erstellen Sie eine Liste der Mitarbeiter (Personalnummer und Nachname) die keine Hardware von IBM besitzen.

select m.Personalnummer, Nachname
from Mitarbeiter m 
where not exists
      (select * from Hardware h where h.Personalnummer=m.Personalnummer and Hersteller = 'IBM')


-- oder

select Personalnummer, Nachname
from Mitarbeiter
where Personalnummer not in
      (select Personalnummer from Hardware where Hersteller = 'IBM')