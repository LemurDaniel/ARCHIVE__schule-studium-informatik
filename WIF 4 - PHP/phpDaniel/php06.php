<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Formular an ein PHP Skript senden und dort auswerten</title>
</head>

<body>

<h1>Photowettbewerb</h1>
Bitte geben Sie Daten ein und laden sie ein Bild hoch<br><br>

<form id="form1" name="form1" method="post" action="php6Auswertung.php" enctype="multipart/form-data">
	<br>Name:<input type="text" name="nachname">
	<br>Vorname:<input type="text" name="vorname">
	<br>Ort:<input type="text" name="ort"><br>
	
	<input type="hidden" name="MAX_FILES_SIZE" value="2000">	<br> 
	
	<br>Bild zum Hochladen: <input type="file" name="fupload">
	<br><input type="submit" value="senden">
	<input type="reset" value="löschen">
</form>

</body>
</html>