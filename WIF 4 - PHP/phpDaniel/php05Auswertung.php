<?php
	//Formularinhalte nach PHP übernehmen
	$nachn	=$_GET["nachname"];	//Name des Inputfeldes
	$vorn	=$_GET["vorname"];
	echo "Sie haben an den Server folgende Daten gesendet:<br>";
	echo "<h2>Nachname: ".$nachn." <br>";
	echo "Vorname:  ".$vorn." <br>";
	echo "Ort:		".$_GET["ort"];
	echo "</h2>";
?>