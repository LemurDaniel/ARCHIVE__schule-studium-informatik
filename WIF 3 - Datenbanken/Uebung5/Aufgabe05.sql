--  5.	Wie viele Notebooks hat das Unternehmen?

select count(*) as [Anzahl der Notebooks des Unternehmens]
from Hardware
where Ger�tetyp = 'Notebook'

-- eine m�gliche (aufwendigere Variante)

select Ger�tetyp, count(*) as Anzahl
from Hardware
group by Ger�tetyp
having Ger�tetyp = 'Notebook'
