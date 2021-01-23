--  24.	Erstellen Sie eine Update-Anweisung, um für alle Mitarbeiter den Grundlohn von DM auf € umzurechnen.
--      Dabei soll gleichzeitig die Kündigungsfrist für alle Mitarbeiter auf 6 Monate festgesetzt werden.

update Dienstvertrag
set Grundlohn = Grundlohn/1.95583,
    Kündigungsfrist = 6
where Vertragsart in (select Vertragsart from Stelle) -- es werden nur die Vertragsarten
                                                      -- berücksichtigt zu denen es Tatsächlich
						      -- eine Stelle gibt