<?php
	// Formularinhalte nach PHP übernehmen
	// und in eine Session Variable speichern

	session_start();
	echo session_id();
	echo "<br>";
	$_SESSION["SName"] = $_GET["nachname"];
	$_SESSION["SVorname"] = $_GET["vorname"];
	$_SESSION["SOrt"] = $_GET["ort"];
	$_SESSION["SBilder"] = $_GET["anzahlBilder"];

	/*
	$nachn	=$_GET["nachname"];	//Name des Inputfeldes
	$vorn	=$_GET["vorname"];
	echo "Sie haben an den Server folgende Daten gesendet:<br>";
	echo "<h2>Nachname: ".$nachn." <br>";
	echo "Vorname:  ".$vorn." <br>";
	echo "Ort:		".$_GET["ort"];
	echo "</h2>"; 
	*/
	echo "Ihre Daten wurden in einer Sessionvariable gespeichert!";
?>