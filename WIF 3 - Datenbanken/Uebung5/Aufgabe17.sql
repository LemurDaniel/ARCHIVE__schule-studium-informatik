--  17.	Erstellen Sie eine Ger�teliste: pro Ger�t sollen die Anzahl der Software Lizenzen
--      sowie deren Gesamtwert ausgewiesen werden. Wie viele Softwarelizenzen mit welchem Gesamtwert hat das Unternehmen?

select h.Ger�tenummer, Ger�tetyp, count(Lizenznummer) as [Anzahl Softwarelizenzen], sum(s.Einkaufspreis) as [Wert der Lizenzen]
from Hardware h join Software s on h.Ger�tenummer=s.Ger�tenummer
group by h.Ger�tenummer, Ger�tetyp

-- Wie viele Softwarelizenzen mit welchem Gesamtwert hat das Unternehmen? geht nur �ber eigene Abfrage
select count(Lizenznummer) as [Anzahl Softwarelizenzen], sum(Einkaufspreis) as [Wert der Lizenzen]
from Software