--  26.	Erstellen Sie eine Hardwareliste (Gerätenummer, Gerätetyp, Bezeichnung) der Geräte
--      auf denen Produkte des Herstellers Corel zu Einsatz kommen.
--      Verwenden Sie dazu eine Unterabfrage mit dem IN-Operator.

Select Gerätenummer, Gerätetyp, Bezeichnung
from Hardware
where Gerätenummer in 
     (select Gerätenummer from Software s join Softwarebezeichnung sb on s.Softwarekürzel=sb.Softwarekürzel
	  where Hersteller = 'Corel')