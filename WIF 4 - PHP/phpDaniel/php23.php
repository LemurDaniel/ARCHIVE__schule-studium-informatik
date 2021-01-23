<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>XML Validieren mit DTD und DOM Objekt</title>
</head>

<body>
<?php 
	error_reporting(E_ALL ^ E_NOTICE);
	$dom = new DOMDocument();
	$dom->preserveWhiteSpace=false;
	$dom->load("./xml/fuhrparkMitDTD.xml");

	var_dump($dom->validate());
	
	$dom = new DOMDocument();
	$dom->preserveWhiteSpace=false;
	$dom->load("./xml/fuhrpark.xml");
	
	var_dump($dom->schemaValidate('./xml/fuhrpark.xsd'));
	?>
</body>
</html>