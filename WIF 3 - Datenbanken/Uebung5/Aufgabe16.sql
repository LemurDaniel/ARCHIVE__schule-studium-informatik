--  16.	Erstellen Sie eine Hardwareliste:
--      pro Mitarbeiter soll die jeweils zugewiesene Hardware aufgezeigt werden. 

select m.Personalnummer, Nachname, Ger�tenummer, Ger�tetyp
from Mitarbeiter m join Hardware h on m.Personalnummer=h.Personalnummer
order by m.Personalnummer