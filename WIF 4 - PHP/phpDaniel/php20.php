<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>XML Parsing mit XMLReader</title>
</head>

<body>
<?php 
$xml_inhalt = new XMLReader();
$xml_inhalt->open("./xml/fuhrpark.xml");
	
	while($xml_inhalt->read()){
		$name = $xml_inhalt->name;
		$nodeType= $xml_inhalt->nodeType;
		$hasAttr = $xml_inhalt->hasAttributes;
		$attriCount = $xml_inhalt->attributeCount;
		$hasVal = $xml_inhalt->hasValue;
		$val = $xml_inhalt->value;
		echo "Knotenname: $name Kontentyp: $nodeType Attribute: $hasAttr AttributeCount: $attriCount Wert: $hasVal Wert: $val <br>";
	}
	
	
?>
</body>
</html>