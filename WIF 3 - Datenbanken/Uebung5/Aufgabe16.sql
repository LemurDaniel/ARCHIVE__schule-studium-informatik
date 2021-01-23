--  16.	Erstellen Sie eine Hardwareliste:
--      pro Mitarbeiter soll die jeweils zugewiesene Hardware aufgezeigt werden. 

select m.Personalnummer, Nachname, Gerätenummer, Gerätetyp
from Mitarbeiter m join Hardware h on m.Personalnummer=h.Personalnummer
order by m.Personalnummer