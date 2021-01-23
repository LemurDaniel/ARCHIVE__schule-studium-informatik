--21. Bestimmen Sie Farbe, Hubraum und Marke der/des Firmenfahrzeuge(s) mit dem größten Ge-samtgewicht,
--d.h. es gibt kein Fahrzeug mit höherem Gewicht.

select Farbe, Hubraum, Marke, Gesamtgewicht
from Firmenfahrzeuge
where Gesamtgewicht = (select max(Gesamtgewicht) from Firmenfahrzeuge)
