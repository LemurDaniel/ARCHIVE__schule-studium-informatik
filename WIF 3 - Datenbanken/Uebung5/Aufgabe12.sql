-- 11.	Erstellen Sie eine Hardwareliste (Gerätenummer, Hardwarehersteller, Softwareherstel-ler, Softwarebezeichnung) mit Software
-- die nicht von Microsoft ist und zur Softwarekategorie „Tabellenkalkulation“ gehört.

select h.Gerätenummer, h.Hersteller Hardwarehersteller, sb.Bezeichnung, sb.Hersteller Softwarehersteller
from Hardware h join Software s on h.Gerätenummer=s.Gerätenummer join Softwarebezeichnung sb on s.Softwarekürzel=sb.Softwarekürzel
where sb.Hersteller<>'Microsoft' and sb.Kategorie='Tabellenkalulation'
                                                 --Vorsicht Fehler: in DB steht "Tabellenkalulation" statt "Tabellenkalkulation"
order by h.Hersteller