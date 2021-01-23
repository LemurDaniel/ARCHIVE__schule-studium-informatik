--  25.	Via Update-Anweisung soll der Grundlohn der Vertragsart c5 um 5% angehoben werden.

update Dienstvertrag
set Grundlohn = Grundlohn*0.05
where Vertragsart = 'c5'