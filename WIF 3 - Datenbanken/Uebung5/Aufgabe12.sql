-- 11.	Erstellen Sie eine Hardwareliste (Ger�tenummer, Hardwarehersteller, Softwareherstel-ler, Softwarebezeichnung) mit Software
-- die nicht von Microsoft ist und zur Softwarekategorie �Tabellenkalkulation� geh�rt.

select h.Ger�tenummer, h.Hersteller Hardwarehersteller, sb.Bezeichnung, sb.Hersteller Softwarehersteller
from Hardware h join Software s on h.Ger�tenummer=s.Ger�tenummer join Softwarebezeichnung sb on s.Softwarek�rzel=sb.Softwarek�rzel
where sb.Hersteller<>'Microsoft' and sb.Kategorie='Tabellenkalulation'
                                                 --Vorsicht Fehler: in DB steht "Tabellenkalulation" statt "Tabellenkalkulation"
order by h.Hersteller