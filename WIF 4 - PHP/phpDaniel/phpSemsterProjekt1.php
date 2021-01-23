<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Unbenanntes Dokument</title>
<style>
	body{
		font-size: 25px;
		font-family: Gill Sans, Gill Sans MT, Myriad Pro, DejaVu Sans Condensed, Helvetica, Arial," sans-serif";
	}
	
	label, input{
		float: left;
		display: block;
		width: 300px;
		margin: "10,10,10,10";
	}	
</style>

</head>

<body>
<?php 
	$mysql = new MySQLi( 'localhost', 'root', '', 'bistro');
	if(!$mysql)die("Keine Verbindung zur Datenbank möglich");
	
	
	echo "<table width='1200' border='1'>
  <tbody>
    <tr>
      <th>Id</th>
      <th>ArtNummer</th>
      <th>ArtBezeichnung</th>
      <th>ArtBeschreibung</th>
      <th>ArtGruppe</th>
      <th>Menge</th>
      <th>Eigenschaft</th>
      <th>Preis</th>
    </tr>";
	
	
	$sql = "Select * from menue Order By ArtGruppe ASC";
	$result = $mysql->query($sql);
	if($result){
		while($row=$result->fetch_array(MYSQLI_NUM)) { 
			$ausgabe = "<tr>";
			for($i=0; $i<8; $i++) $ausgabe .=  "<td>".$row[$i]."</td>";
			$ausgabe .= "</tr>";
			echo $ausgabe;
		}
		
		echo "</tbody></table>";
	}
?>

<div class="Container">
	<form enctype="multipart/form-data" action="<?php print $_SERVER['PHP_SELF'] ?>" method="post">

	<input type="radio" name="gruppe" id="gruppe" checked> Getränk
	<input type="radio" name="gruppe" id="gruppe"> Speise<br>
	<label for="artNummer">Artikel Nummer:</label>
	<input type="number" min="99999" max="1000000" name="artNummer" id="artNummer"><br>
	<label for="bezeichnung">Artikel Bezeichnung:</label>
	<input type="text" name="bezeichnung" id="bezeichnung"><br>
	<label for="beschreibung">Artikel Beschreibung:</label>
	<input type="text" name="beschreibung" id="beschreibung"><br>
	<label for="menge">Menge:</label>
	<input type="text" name="menge" id="menge"><br>
	<label for="eigenschaft">Eigenschaft:</label>
	<input type="text" name="eigenschaft" id="eigenschaft"><br>
	<label for="preis">Preis:</label>
	<input type="number" name="preis" id="preis"><br>

	<input type="submit" value="Speichern">
	<input type="Reset">
	</form>
</div>

</body>
</html>