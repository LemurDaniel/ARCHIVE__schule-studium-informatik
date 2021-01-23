-- 14.	Erstellen Sie eine Liste von Herstellern (Hersteller) von denen Sie sowohl Software als auch Hardware haben.
-- Jeder Hersteller soll nur einmal aufgef�hrt sein. Zeigen Sie daf�r mindesten zwei Varianten der Abfrage.

select distinct h.Hersteller
from Hardware h join Softwarebezeichnung sb on h.Hersteller=sb.Hersteller

select Hersteller from Hardware
intersect
select Hersteller from Softwarebezeichnung

