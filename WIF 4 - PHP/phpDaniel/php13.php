<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 13 - Daten formatiert ausgeben</title>
</head>

<body>
<pre>
<?php
	$menge=100;
	$artikel="Schrauben M8";
	$preis=0.05;
	$netto=$preis*$menge;
	$brutto=$netto*1.19;
	
	// Format 1: Auffüllen mit <leer> insgesamt 10 breit keine Nachkommastellen float;
	printf("<br> % 10.0f Stück kosten netto: %; 9.2F Euro<br>", $menge, $netto);




	$Endsumme = 121424.125125;
	$summeenglisch= number_format($Endsumme, 2, ".", ",");
	echo "<br><br> $summeenglisch";


	echo "<br><br>";
	printf("% -20.s % -15.s % 15.s % 15.s <br>", "Artikel", "Menge", "Preis", "Brutto");
	printf("% -20.s % -15.s % 15.s % 15.s <br>", $artikel, "$menge Stück", "$preis Euro", "$brutto Euro");
	printf("% -20.s % -15.s % 15.s % 15.s <br>", "Schraube Torx M4", "100 Stück", "500 Euro", "595 Euro");
?>
</pre>
</body>
</html>