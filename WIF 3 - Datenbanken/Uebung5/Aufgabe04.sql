--  4.	Wie viele Mitarbeiter stammen aus �sterreich?

Select count(*) as [Anzahl der Mitarbeiter aus �stereich]
from Mitarbeiter
where L�nderkennzeichen = 'A'