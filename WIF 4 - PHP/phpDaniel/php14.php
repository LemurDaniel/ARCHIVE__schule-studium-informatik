<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 14 - Datum Uhrzeit</title>
</head>

<body>
<?php
	$datum=getdate();
	print_r($datum);
	
	echo "<br>";
	
	echo "<br>Tag: 		".$datum["mday"];
	echo "<br>Monat: 	".$datum["mon"];
	echo "<br>Jahr: 	".$datum["year"];
	
		
	echo "<br>";
	// Formatierung des aktuellen Dates
	echo "<br>Datum: ".date("D \d\\e\\r d M Y \u\m H:i:s \U\h\\r");
?>
</body>
</html>