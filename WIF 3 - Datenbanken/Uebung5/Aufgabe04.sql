--  4.	Wie viele Mitarbeiter stammen aus Österreich?

Select count(*) as [Anzahl der Mitarbeiter aus Östereich]
from Mitarbeiter
where Länderkennzeichen = 'A'