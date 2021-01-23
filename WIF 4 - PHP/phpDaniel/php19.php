<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP19 - Generieren von XML Strukturen - PKW Fuhrpark</title>
</head>

<body>
<h1>Start des Simple XML</h1>
<?php 
	// XML Struktur für PKW Fuhrpark
	$root = simplexml_load_string('<fuhrpark></fuhrpark>');
	
	$pkw = $root->addChild('pkw', '');
	$pkw->addChild('hersteller', 'Ford');
	$pkw->addChild('typ', 'Mustang');
	
	$techdat = $pkw->addChild('techdat', '');
	$techdat->addAttribute('Metrik', 'US');
	
	$techdat->addChild('Leistung', '230hp');
	$techdat->addChild('Beschleunigung', '7 sec auf 100mph');
	
	
	$pkw = $root->addChild('pkw', '');
	$pkw->addChild('hersteller', 'VW');
	$pkw->addChild('typ', 'Golf GTI');
	
	$techdat = $pkw->addChild('techdat', '');
	$techdat->addAttribute('Metrik', 'US');
	
	$techdat->addChild('Leistung', '300hp');
	$techdat->addChild('Beschleunigung', '5 sec auf 100mph');
	$techdat->addChild('Verbrauch', '9 l auf 100 km');
	
	// Ausgabe
	echo $root->asXML();
	if($root->asXML("xml/fuhrparkpkw.xml")){
		echo "<br><br>Fuhrpark wurde angelegt";
	}
	
?>
</body>
</html>