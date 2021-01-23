--  29.	Erstellen Sie eine Liste der Hardware (Gerätenummer, Gerätetyp, Anzahl der Lizenzen) mit mehr
--      als drei Lizenzen, die jeweils mehr als 100€ kosten.

select h.Gerätenummer, h.Gerätetyp, count(Lizenznummer) as [Anzahl der Lizenzen]
from Hardware h join Software s on h.Gerätenummer=s.Gerätenummer
where s.Einkaufspreis>100
group by h.Gerätenummer, h.Gerätetyp
having count(Lizenznummer)>3