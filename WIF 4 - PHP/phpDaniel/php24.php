<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP24 - COOKIES</title>
</head>

<body>

<?php 
if(isset($_COOKIE['surfer'])){
	$cookieinf = explode(' ',$_COOKIE['surfer']);	// nach Datum und Uhrzeit zerlegt
	echo "Guten Tag, Sie besuchten uns das letzte Mal am: ".$cookieinf[0]." es war um ".$cookieinf[1]." Uhr<br>";
} else {
	echo "Sie waren noch nie auf unserer Seite - schön dass Sie uns gefunden haben<br>";		
	$datum = getdate();
	$kennung=$datum["mday"].".".$datum["mon"].".".$datum["year"]." ".$datum["hours"].":".$datum["minutes"].":".$datum["seconds"];
	setcookie("surfer", $kennung, time()+3600*24*7);
	echo "Es wurde ein Cookie gesetzt, es ist eine Woche gültig";
}

?>
</body>
</html>