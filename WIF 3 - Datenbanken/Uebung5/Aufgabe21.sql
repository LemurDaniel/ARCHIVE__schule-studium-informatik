--21. Bestimmen Sie Farbe, Hubraum und Marke der/des Firmenfahrzeuge(s) mit dem gr��ten Ge-samtgewicht,
--d.h. es gibt kein Fahrzeug mit h�herem Gewicht.

select Farbe, Hubraum, Marke, Gesamtgewicht
from Firmenfahrzeuge
where Gesamtgewicht = (select max(Gesamtgewicht) from Firmenfahrzeuge)
