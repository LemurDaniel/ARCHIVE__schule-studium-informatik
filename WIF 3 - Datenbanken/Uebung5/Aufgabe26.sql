--  26.	Erstellen Sie eine Hardwareliste (Ger�tenummer, Ger�tetyp, Bezeichnung) der Ger�te
--      auf denen Produkte des Herstellers Corel zu Einsatz kommen.
--      Verwenden Sie dazu eine Unterabfrage mit dem IN-Operator.

Select Ger�tenummer, Ger�tetyp, Bezeichnung
from Hardware
where Ger�tenummer in 
     (select Ger�tenummer from Software s join Softwarebezeichnung sb on s.Softwarek�rzel=sb.Softwarek�rzel
	  where Hersteller = 'Corel')