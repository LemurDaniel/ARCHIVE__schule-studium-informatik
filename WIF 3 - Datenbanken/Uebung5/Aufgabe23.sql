--  23.	Erstellen Sie eine Liste der Angestellten mit ihren Gesamtkosten für Seminare und der Anzahl der Seminartage;
--      (Personalnummer, Nachname, Vorname, Seminarkosten, Seminartage).
--      Wie viel Geld und Tage wurden insgesamt für Seminare aufgebracht?

select m.Personalnummer, m.Nachname, m.Vorname, sum(Preis) as [Gesamtkosten für Seminare], sum(day(Datumbis-Datumvon)) as [Anzahl der Seminartage]
from Mitarbeiter m join Seminarbesuche sb on m.Personalnummer=sb.Personalnummer
     join Seminare s on sb.Seminarnummer=s.Seminarnummer
group by  m.Personalnummer, m.Nachname, m.Vorname


--      Wie viel Geld und Tage wurden insgesamt für Seminare aufgebracht? --> eigene Abfrage
select sum(Preis) as [Gesamtkosten für Seminare], sum(day(Datumbis-Datumvon)) as [Anzahl der Seminartage]
from Seminarbesuche sb join Seminare s on sb.Seminarnummer=s.Seminarnummer