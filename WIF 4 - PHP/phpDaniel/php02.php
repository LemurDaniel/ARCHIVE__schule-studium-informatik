<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP2 - Diverses</title>
</head>

<body>
<?php
/* einige Beispiele zur Syntax und zum Thema ' "
und ein Beispiel für include*/
	include_once "VarDef.php";
	$preispackung = $preis*$packeinheit;
// Ausgabezeile zusammenstellen
	$ausgabezeile1="<br>Der Artikel ".$artikel." zum Einzelpreis von ".$preis." €<br>";
	$ausgabezeile2="hat eine Packung mit ".$packeinheit." Stück und kostet pro Einheit ".$preispackung." €<br>";
		echo $ausgabezeile1;
		echo $ausgabezeile2;
	
// Und hier ein Beispiel mit '<br>
		echo 'Die Variable heißt: $preispackung und den Inhalt '."$preispackung";
?>


</body>
</html>