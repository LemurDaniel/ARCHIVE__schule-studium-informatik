<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP8 - 1</title>
</head>

<body>
	<h1>Sortieren von Arrays</h1>
<?php 
	$temperaturen = array (29.1, 22.3, 14.3, 27.6, 31.2, 29.1, 33.1); //Irgendwelche Temperaturen<br>
	$anzahl = sizeof($temperaturen);
	
	// Ausgabe des Arrays unsortiert
	for ($i=0; $i<$anzahl; $i++)
		echo "$temperaturen[$i] "; 
	echo " unsortiert<br>";
	
	// Ausgabe aufsteigend SORT NUMERIC und SORT STRING
	sort($temperaturen, SORT_NUMERIC);
	for ($i=0; $i<$anzahl; $i++)
		echo "$temperaturen[$i] "; 
	echo " sortiert aufsteigend<br>";
	
	// Ausgabe aufsteigend SORT NUMERIC und SORT STRING
	rsort($temperaturen, SORT_NUMERIC);
	for ($i=0; $i<$anzahl; $i++)
		echo "$temperaturen[$i] "; 
	echo " sortiert absteigend<br>";
?>

</body>
</html>