<?php
	//Formularinhalte nach PHP übernehmen
	echo "Sie haben an den Server folgende Daten gesendet:<br>";
	
	if (isset( $_FILES["fupload"] )) {	//isset true wenn Array gesetzt
		$nachn	=$_POST["nachname"];	//Name des Inputfeldes
		$vorn	=$_POST["vorname"];
		echo "<h2>Nachname: ".$nachn." <br>";
		echo "Vorname:  ".$vorn." <br>";
		echo "Ort:		".$_POST["ort"];
		echo "</h2>";
		
		$source=$_FILES['fupload']['tmp_name'];	//Hier steht der Name der temporären Datei<br>
		$target="D://xampp/apache/Bilder/".$_FILES['fupload']['name'];	//Orginalname der Datei<br>
		// Zielverzeichnis in dem die Datei hochgeladen wird<br>
		
		if($source<>""){	//Wenn source nicht leer
			move_uploaded_file($source, $target) or die ("Fehler bei Übertragung");
			echo "Ihre Datei wurde gespeichert";
		} else {
			echo "keine Datei angegeben";
		} 
		// else {
		//	echo "Fehler Datei ist zu groß";
		// }
	}
?>