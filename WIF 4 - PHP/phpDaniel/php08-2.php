<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP-2 Assoziative Arrays</title>
</head>

<body>
<?php
	//Indizierte Felder<br>
	$staedte=array("Rom", "London", "Paris", "Berlin");
	$staedte[]="Ansbach"; // Anhängen eines Elements
	for ($ind=0; $ind<=count($staedte)-1; $ind++)
		echo "- $staedte[$ind] <br>";
	
	//Assoziative Arrays<br>
	$lufttemp=array ("Rom" => "28 Grad",
					 "London" => "14 Grad",
					 "Paris" => "30 Grad",
					 "Berlin" => "20 Grad",
					);
	$lufttemp["Ansbach"] = "24 Grad";
	
	foreach($lufttemp as $stadt=>$temp){
		echo "<br>In $stadt hat es $temp";
	}
	
	echo "<br>--------------------------------------------------------<br><br>";
		
	//Sortieren von Assoziativen Arrays
	//nach Werten - hier nach Temperatur - asort
	asort($lufttemp, SORT_STRING);
	foreach($lufttemp as $stadt=>$temp){
		echo "<br>In $stadt hat es $temp";
	}
	
	echo "<br>--------------------------------------------------------<br><br>";
	
		//Sortieren von Assoziativen Arrays
	//nach Index - hier nach Stadt - ksort
	ksort($lufttemp, SORT_STRING);
	foreach($lufttemp as $stadt=>$temp){
		echo "<br>In $stadt hat es $temp";
	}
?>
</body>
</html>