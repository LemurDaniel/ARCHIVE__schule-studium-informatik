<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP10 - 1 Datei schreiben hier Zugriffszähler</title>
</head>

<body>
<h3>	Herzlich Willkommen! Sie sind Besucher: <?php counter() ?> </h3>

<?php
	function counter(){
		$datei=fopen("count.txt", "r+"); //Öffnen zum lesen und Schreiben
		
		if($datei){
				flock($datei, LOCK_EX);
			
			$counter= fgets($datei, 10);	// Counter maximal 10 stellig
			fseek($datei,0);         		// Dateizeiger ganz nach vorne
			$counter += 1;					// Counter erhöhen
			echo "<b> $counter </b>";		// Wert des Counters ausgeben
			fwrite($datei, $counter);		// Erhöhten Counter zurückschreiben
			
			sleep(1000);
			
				flock($datei, LOCK_UN); 
			fclose($datei);

		}else{
			echo "Problem bei Dateizugriff";
		}
	}
?>


</body>
</html>