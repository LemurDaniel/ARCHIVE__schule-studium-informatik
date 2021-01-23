--7. Finde die Bezeichnungen aller Seminare von „debis Systemhaus Training“

select s.Bezeichnung
from Seminaranbieter sa join Seminare s on sa.AnbieterNr=s.AnbieterNr
where sa.Anbieter='debis Systemhaus Training'