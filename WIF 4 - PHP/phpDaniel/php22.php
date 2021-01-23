<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>XML Parsen mit DOM Objekt</title>
</head>

<body>
<?php 
	error_reporting(E_ALL ^ E_NOTICE);
	$dom = new DOMDocument();
	$dom->preserveWhiteSpace=false;
	$dom->load("./xml/fuhrpark.xml");
	$lkws = $dom->getElementsByTagname("lkw");
	foreach($lkws as $lkw){
		echo "<br> - ".$lkw->firstChild->nodeName.": ".$lkw->firstChild->firstChild->data.'<br>';
	}
	?>
</body>
</html>