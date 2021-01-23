<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>XML lesen mit XMLReader</title>
</head>

<body>
<?php
	$xml_inhalt = new XMLReader();
	$xml_inhalt->open("./xml/fuhrpark.xml");

		while($xml_inhalt->read()){
			if($xml_inhalt->nodeType==XMLReader::ELEMENT){
				if ($xml_inhalt->hasAttributes){ echo "<br>Fahrzeug vom Typ: ".$xml_inhalt->getAttribute("typ")."<br>";}
			
				if($xml_inhalt->name=="hersteller"){echo "Hersteller ist: ";}
				if($xml_inhalt->name=="fahrer"){echo "Fahrer ist: ";}
			}
			if($xml_inhalt->nodeType==XMLReader::TEXT){ echo $xml_inhalt->value."<br>"; }
		}
	?>
</body>
</html>