--  5.	Wie viele Notebooks hat das Unternehmen?

select count(*) as [Anzahl der Notebooks des Unternehmens]
from Hardware
where Gerätetyp = 'Notebook'

-- eine mögliche (aufwendigere Variante)

select Gerätetyp, count(*) as Anzahl
from Hardware
group by Gerätetyp
having Gerätetyp = 'Notebook'
