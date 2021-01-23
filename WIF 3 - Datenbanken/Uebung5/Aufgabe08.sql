--  8.	Erstellen Sie eine Liste der Mitarbeiter
--      (Personalnummer, Nachname, Vorname, Titel, Vertragsart, Besch�ftigungsart und K�ndigungsfrist). 

select m.Personalnummer, Nachname, Vorname, Titel, d.Vertragsart, Besch�ftigungsart, K�ndigungsfrist
from Mitarbeiter m join Stelle s on m.Personalnummer=s.Personalnummer
     join Dienstvertrag d on s.Vertragsart=d.Vertragsart