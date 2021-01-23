<?php 
// Überprüfen ob Datei existiert

if(!file_exists("php11-data.csv")){
	echo "Hotelbewertung nicht Möglich - datei existiert nicht";
}
else{
	$datei = fopen("php11-data.csv", "a");
	if(!$datei){
		echo "<p>Datei konnte nicht zum Schreiben geöffnet werden";
	}
	fputs($datei, $_POST["beruf"].";".$_POST["alter"].";".$_POST["lage"].";".$_POST["sauberkeit"].";".$_POST["service"].";".$_POST["gesamturteil"].";\n");

	echo "Vielen Dank für ihre Bewertung";
	echo "<p>Zurück zur <a href='php11.php'> Eingabe";
}

?>
