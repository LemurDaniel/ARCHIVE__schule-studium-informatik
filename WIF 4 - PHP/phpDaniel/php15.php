<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 15 - DB Lesen</title>
</head>

<body>
<pre>
<?php 
	header("Content-Type: text/html; charset=utf-8");
	
	$mysql = new MySQLi( 'localhost', 'root', '', 'fotowettbewerb');
	
	if(!$mysql){
		die("Keine Verbindung zur Datenbank möglich");
	}
	
	//mysqli_query($mysql, "SET NAMES 'utf8'");
	$sql = "Select * From teilnehmer";
	$result = $mysql->query($sql);
	
 	echo "<h3>";
	printf("<b> % -5.s % -30.s % -30.s % -30.s % -40.s % -30.s % -30.s</b><br>", "Id", "Vorname", "Name", "Ort", "Email", "Bild1", "Bild2");
	if($result){
		while($row=$result->fetch_array(MYSQLI_NUM)) { 
			printf(" % -5.s % -30.s % -30.s % -30.s % -40.s % -30.s % -30.s<br>", $row[0], $row[2], $row[1], $row[3], $row[4], $row[5], $row[6]);
		}
	}else{
		echo "<br><br> <h1>";
		echo "<br>!!!Keine Ergebnisse!!!";
		echo "<br>Fehlernummer: ".$mysql->errno;
		echo "<br>DBSTATE: ".$mysql->sqlstate;
		echo "</h1>";
	}
	 echo "</h3>";

	$mysql->close();
?>
</pre>
</body>
</html>