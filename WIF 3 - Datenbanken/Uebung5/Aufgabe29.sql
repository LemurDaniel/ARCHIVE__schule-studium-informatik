--  29.	Erstellen Sie eine Liste der Hardware (Ger�tenummer, Ger�tetyp, Anzahl der Lizenzen) mit mehr
--      als drei Lizenzen, die jeweils mehr als 100� kosten.

select h.Ger�tenummer, h.Ger�tetyp, count(Lizenznummer) as [Anzahl der Lizenzen]
from Hardware h join Software s on h.Ger�tenummer=s.Ger�tenummer
where s.Einkaufspreis>100
group by h.Ger�tenummer, h.Ger�tetyp
having count(Lizenznummer)>3