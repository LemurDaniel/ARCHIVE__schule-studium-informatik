--  18.	Erstellen Sie eine Liste der Angestellten mit ihren Jahresgeh�ltern (Grundlohn * Monatsgeh�lter);
--      (Personalnummer, Nachname, Vorname, Grundlohn, Monatsgeh�lter, Jahresgehalt)

select m.Personalnummer, Nachname, Vorname, Grundlohn, Monatsgeh�lter, Grundlohn * Monatsgeh�lter as Jahresgehalt
from Mitarbeiter m join Stelle s on m.Personalnummer=s.Personalnummer
     join Dienstvertrag d on s.Vertragsart=d.Vertragsart