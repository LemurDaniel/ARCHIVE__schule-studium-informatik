<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP18 - Parsen einer XML mit SAX</title>
</head>

<body>
<?php 
	
	function start($parser, $name, $attribute){
		switch($name){
			// Parser liefert XML Namen in Großbuchstaben
			case 'FUHRPARK':	echo '<h1>Der Fuhrpark der Firma Logistica</h1><ul>'; break;
			case 'LKW': 		echo '<li><ul>LKW vom Typ: '.implode('', $attribute); break;
			case 'HERSTELLER': 	echo '<li>Hersteller:  '; break;
			case 'FAHRER': 		echo '<ul><li>Fahrer: '; break;
		}
	}
	
	// Handler für Schließen von Tags
	function ende($parser, $name){
		switch($name){
			case 'FUHRPARK':	echo '</ul>'; break;
			case 'LKW': 		echo '</ul></li>'; break;
			case 'HERSTELLER':	echo '</li>'; break;
			case 'FAHRER': 		echo '</li></ul>'; break;
		}
	}
	
	//Handler wird bei Text aufgerufen
	function cdata($parser, $daten){
		echo $daten;
	}
	
	echo "Start des XML SAX Parsers";
	$parser = xml_parser_create();
	xml_set_element_handler($parser, 'start', 'ende');
	xml_set_character_data_handler($parser, 'cdata');
	xml_parse($parser, file_get_contents('xml/fuhrpark.xml'), true);
	echo "<br>Ende des SAX Parsings"
?> 
</body>
</html>