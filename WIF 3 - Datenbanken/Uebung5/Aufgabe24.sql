--  24.	Erstellen Sie eine Update-Anweisung, um f�r alle Mitarbeiter den Grundlohn von DM auf � umzurechnen.
--      Dabei soll gleichzeitig die K�ndigungsfrist f�r alle Mitarbeiter auf 6 Monate festgesetzt werden.

update Dienstvertrag
set Grundlohn = Grundlohn/1.95583,
    K�ndigungsfrist = 6
where Vertragsart in (select Vertragsart from Stelle) -- es werden nur die Vertragsarten
                                                      -- ber�cksichtigt zu denen es Tats�chlich
						      -- eine Stelle gibt