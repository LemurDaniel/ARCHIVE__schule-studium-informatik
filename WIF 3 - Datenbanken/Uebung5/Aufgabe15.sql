-- 15.	Erstellen Sie eine Liste der Mitarbeiter (Nachname, Grundlohn), deren Grundlohn mindestens 300€ über dem
-- durchschnittlichen Grundlohn aller Mitarbeiter liegt.

select Nachname, d.Grundlohn
from Mitarbeiter m join Stelle s on m.Personalnummer=s.Personalnummer join Dienstvertrag d on d.Vertragsart=s.Vertragsart
where d.Grundlohn >= (select AVG(Grundlohn) from Dienstvertrag) + 300