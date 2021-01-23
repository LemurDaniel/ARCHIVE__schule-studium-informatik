-- 13.	Erstellen Sie eine Liste der Mitarbeiter (Personalnummer, Nachname, Seminarnummer, Seminarbezeichnung), die Seminare besucht haben,
-- in deren Bezeichnung der Begriff „System“ vorkommt.

select m.Personalnummer, Nachname, s.Seminarnummer, s.Bezeichnung
from Mitarbeiter m join Seminarbesuche sb on m.Personalnummer=sb.Personalnummer join Seminare s on s.Seminarnummer=sb.Seminarnummer
where s.Bezeichnung like '%system%'