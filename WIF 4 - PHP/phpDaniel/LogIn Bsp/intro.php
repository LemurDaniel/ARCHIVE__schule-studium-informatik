<!doctype html>

<?php 
	session_start();
	if(isset($_POST["name"])){
		if($_POST["name"]=="Zilker" && $_POST["pw"]=="geheim" || $_POST["name"]=="Meier" && $_POST["pw"]=="vielgeheimer"){
			$_SESSION["name"] = $_POST["name"];
		}
	}
	include "kontrolle.php";
?>


<html>
<head>
<meta charset="utf-8">
<title>Intro Seite mit Begrüßung</title>
</head>

<body>		
<h1>Intro Seite mit Begrüßung</h1>
<?php
	echo "Guten Tag Frau oder Herr ".$_SESSION["name"]."<br>";
?>
<p><a href="inhalt.php">Weitere geschützte Seite</a></p>
<p><a href="index.php">LogOff</a></p>
</body>
</html>