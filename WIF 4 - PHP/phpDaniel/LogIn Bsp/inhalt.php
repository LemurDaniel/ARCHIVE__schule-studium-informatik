<!doctype html>
<?php 
session_start();
include "kontrolle.php";
?>

<html>
<head>
<meta charset="utf-8">
<title>Inhalt</title>
</head>

<body>
<h1>Musterseite für geschützte Inhalte</h1>
<h1>User soll mit Namen begrüßt werden</h1>
<?php 
	echo "Guten Tag Frau oder Herr ".$_SESSION["name"]."<br>";
	echo "Hier folgen irgendwelche Infos";
?>
<p><a href="intro.php">Intro Seite</a></p>
<p><a href="index.php">LogOff</a></p>
</body>
</html>