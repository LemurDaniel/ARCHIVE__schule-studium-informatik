<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP17 - ?</title>
</head>

<body>
<Pre>
<?php
//echo "Inhalt von POST".(isset($_POST['FName'])."<br>");
	
	if(isset($_POST['FName'])){
		$mysql = new MySQLi( 'localhost', 'root', '', 'fotowettbewerb');
	
	if(!$mysql){
		die("Keine Verbindung zur Datenbank möglich");
	}
	
	//mysqli_query($mysql, "SET NAMES 'utf8'");
	$FN=$_POST['FName'];
	$FV=$_POST['FVorname'];
	$FO=$_POST['FOrt'];
	$FE=$_POST['FEmail'];
	$FB1=$_POST['FBild1'];
	$FB2=$_POST['FBild2'];
		
	$sql = "Insert into teilnehmer (vorname, Name, Ort, Email, Bild1, Bild2) values ('$FV', '$FN', '$FO', '$FE', '$FB1', '$FB2');";
	if(!$mysql->query($sql)){
		echo "<br><br> <h1>";
		echo "<br>".$mysql->error;
		echo "<br>!!!Schreibfehler!!!";
		echo "<br>Fehlernummer: ".$mysql->errno;
		echo "<br>DBSTATE: ".$mysql->sqlstate;
		echo "</h1>";
	}
	
	$sql = "Select * from teilnehmer";
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
		
	}
?>
</Pre>

<form enctype="multipart/form-data" action="<?php print $_SERVER['PHP_SELF'] ?>" method="post">
	<p>
		Name: 		<input type="text" name="FName"><br>
		Vorname: 	<input type="text" name="FVorname"><br>
		Ort:		<input type="text" name="FOrt"><br>
		email:		<input type="text" name="FEmail"><br>
		Bild1:		<input type="text" name="FBild1"><br>
		Bild2:		<input type="text" name="FBild2"><br>
		<input type="submit" value="Daten speichern"><br>
	</p>
</form>
</body>
</html>
