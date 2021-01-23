<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP9 - lesen einer Textdatei</title>
</head>

<body>
<h1>Wetterbericht</h1>

<?php
	if(!file_exists("wetter.txt")){
		echo "Datei konnte nicht gefunden werden";
		exit;
	}
	
	$datei=fopen("wetter.txt", "r");	// Datei öffnen zum lesen
	
	if(!$datei){
		echo "Problem beim Öffnen der Datei";
		exit;
	}
	
	// Datei kann man zeilenweise gelesen werden
	while (!feof($datei)){
		$zeile=fgets($datei);	// Eine Zeile (Satz) lesen
		$komponenten=explode(' ', $zeile); // Zerlegen der Zeile in einzelenen Wert<br>
		echo "<br>In $komponenten[0] ist es $komponenten[2] und es hat $komponenten[1] Grad Celcius";
	}
		fclose($datei); // Schliessen der Datei
?>

</body>
</html>