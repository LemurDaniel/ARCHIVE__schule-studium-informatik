<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 25 - Session</title>
</head>

<body>
<h1>Session aufgreifen und bearbeiten</h1>
<?php	
	echo "Folgende Daten sind in der Session enthalten:<br>";
	echo "<h2>Nachname: ".($_SESSION["SName"])." <br>";
	echo "Vorname:  ".$_SESSION["SVorname"]." <br>";
	echo "Ort:		".$_SESSION["SOrt"];
	echo "Anzahl der Bilder: ".$_SESSION["SBilder"];
	echo "</h2>"; 
	
	session_destroy();
?>
</body>
</html>