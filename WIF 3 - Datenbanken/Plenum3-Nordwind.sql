-- Query 1

select Einzelpreis
from Artikel
order by Einzelpreis desc

select distinct Einzelpreis
from Artikel
order by Einzelpreis desc

select distinct Einzelpreis+15 as [Einzelpreis + 15]
from Artikel
order by [Einzelpreis + 15] desc

-- Query 2
GO

Select	ArtikelNr, Artikelname, Lagerbestand, Einzelpreis, Lagerbestand*Einzelpreis as [Artikel-Wert] 
from Artikel
where lagerbestand>10 and einzelpreis>10
order by Lagerbestand desc, Einzelpreis

select * from Artikel where Einzelpreis is null

select * from Artikel where Einzelpreis is not null

-- Query 3
GO

select Artikelname, Einzelpreis Preis, Lagerbestand Bestand, Einzelpreis*Lagerbestand Wert
from Artikel
where Artikelname not Like'[^i,t]%'

select Count(*) as [Anzahl der Artikel]
from Artikel
where Auslaufartikel=1

select Count(*) as [Anzahl der Artikel], max(Einzelpreis), min(Einzelpreis)
from Artikel
where Auslaufartikel=0