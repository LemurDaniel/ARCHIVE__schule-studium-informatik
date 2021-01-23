<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 12 - Datei Metadaten</title>
</head>

<body>
<?php
	$datei = "wetter.txt";
	$info = stat($datei);
	// test
	
	for($i=1; $i<=10; $i++){
		echo "<br>".$info[$i]." +++";
	}
	
	echo "<br><br>datei: $datei";
	echo "<br>Größe in Byte: $info[7]";
	echo "<br>Letzter Zugriff: ".date("d.m.y", $info[8]);
	echo "<br>Letzte Modifikation: ".date("d.m.y", $info[9]);
	echo "<br>Letzte Änderung: ".date("d.m.y", $info[10]);
?>
</body>
</html>