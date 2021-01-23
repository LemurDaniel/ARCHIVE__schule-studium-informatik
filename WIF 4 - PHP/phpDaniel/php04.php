<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 4 Statische Variablen</title>
</head>

<body>
<?php
	echo "Lokal<br>";
	function zaehler(){
		$x=0;
		echo $x++; echo "<br>";
	}
	zaehler();
	zaehler();
	zaehler();
	
	echo "----------------------";
	echo "<br>Statisch<br>";
	function zaehler2(){
		static $x=0;
		echo $x++; echo "<br>";
	}
	zaehler2();
	zaehler2();
	zaehler2();
	?>
</body>
</html>