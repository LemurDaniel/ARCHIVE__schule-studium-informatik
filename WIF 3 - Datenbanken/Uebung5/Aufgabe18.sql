--  18.	Erstellen Sie eine Liste der Angestellten mit ihren Jahresgehältern (Grundlohn * Monatsgehälter);
--      (Personalnummer, Nachname, Vorname, Grundlohn, Monatsgehälter, Jahresgehalt)

select m.Personalnummer, Nachname, Vorname, Grundlohn, Monatsgehälter, Grundlohn * Monatsgehälter as Jahresgehalt
from Mitarbeiter m join Stelle s on m.Personalnummer=s.Personalnummer
     join Dienstvertrag d on s.Vertragsart=d.Vertragsart