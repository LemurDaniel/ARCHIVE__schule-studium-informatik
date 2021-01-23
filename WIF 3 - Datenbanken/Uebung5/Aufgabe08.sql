--  8.	Erstellen Sie eine Liste der Mitarbeiter
--      (Personalnummer, Nachname, Vorname, Titel, Vertragsart, Beschäftigungsart und Kündigungsfrist). 

select m.Personalnummer, Nachname, Vorname, Titel, d.Vertragsart, Beschäftigungsart, Kündigungsfrist
from Mitarbeiter m join Stelle s on m.Personalnummer=s.Personalnummer
     join Dienstvertrag d on s.Vertragsart=d.Vertragsart