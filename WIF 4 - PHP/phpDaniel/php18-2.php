<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP18 - 2 - Parsen einer XML mit Simple XML</title>
</head>

<body>
<?php 
	echo 'Start des SimpleXML Parsers';
	// Xml Laden
	$xml = simplexml_load_file('xml/fuhrpark.xml');
	// Zugriff auf objekteigenschaften
	// Zugriff auf Attribute mit einem Index
	echo "Die ersten drei LKW sind: <br><ul>";
	echo "<li>".$xml->lkw[0]->hersteller[0]." Fahrer 1: ".$xml->lkw[0]->fahrer[0]."</li>";
	echo "<li>".$xml->lkw[1]->hersteller[0]." Fahrer 1: ".$xml->lkw[1]->fahrer[0]."</li>";
	echo "<li>".$xml->lkw[2]->hersteller[0]." Fahrer 1: ".$xml->lkw[2]->fahrer[0]."</li></ul>";
	
	echo "<br><br><br><br>";
	
	
	echo '<ul>';
	foreach ($xml->lkw as $lkw){
		echo '<li>'.$lkw->hersteller.' hat den Typ: '.$lkw['typ'];
		//Schleife innerhalb LKW über Fahrer
		echo '<ul>';
		foreach($lkw->fahrer as $fahrer){
			echo '<li>'.$fahrer.'</li>';
		}
		echo '</ul>';
	}
	echo '</ul>';
	
?>
</body>
</html>