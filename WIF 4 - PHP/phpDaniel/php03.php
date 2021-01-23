<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP3 Gültigkeit von Variablen</title>
</head>

<body>
<?php
	$x=5;
	function quadrat($a){
		global $x;
		$x=$a*$a;
		echo "Funktion: $x<br>";
	}
	quadrat(5);	//Ausgabe = 25
	echo "Außerhalb: $x<br>";
	echo "<br>------------------------------------------------<br>";
	//Systemvariablen
	echo "Name der php-Datei: ".$_SERVER['PHP_SELF'];
	echo "<br>IP-Adresse des Clients: ".$_SERVER['REMOTE_ADDR'];	//Server IP
?>
</body>
</html>