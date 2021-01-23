--  17.	Erstellen Sie eine Geräteliste: pro Gerät sollen die Anzahl der Software Lizenzen
--      sowie deren Gesamtwert ausgewiesen werden. Wie viele Softwarelizenzen mit welchem Gesamtwert hat das Unternehmen?

select h.Gerätenummer, Gerätetyp, count(Lizenznummer) as [Anzahl Softwarelizenzen], sum(s.Einkaufspreis) as [Wert der Lizenzen]
from Hardware h join Software s on h.Gerätenummer=s.Gerätenummer
group by h.Gerätenummer, Gerätetyp

-- Wie viele Softwarelizenzen mit welchem Gesamtwert hat das Unternehmen? geht nur über eigene Abfrage
select count(Lizenznummer) as [Anzahl Softwarelizenzen], sum(Einkaufspreis) as [Wert der Lizenzen]
from Software