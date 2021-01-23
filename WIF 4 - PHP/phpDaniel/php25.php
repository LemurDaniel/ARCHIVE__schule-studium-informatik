<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP 25 - Session</title>
<style>
	label {
		display: block;
		width: "200";
	}	
</style>
</head>

<body>

<h1>Formular an ein PHP Skript senden und dort in eine Sessionvariable</h1>
Bitte geben Sie Daten ein und senden ein Formular ab<br><br>

<form id="form1" name="form1" method="GET" action="php25Auswertung.php">
	<br><label>Name:</label><input type="text" name="nachname">
	<br><label>Vorname:</label><input type="text" name="vorname">
	<br><label>Ort:</label><input type="text" name="ort">
	<br><label>Anzahl Bilder:</label><input type="text" name="anzahlBilder">
	<br>
	<input type="submit" value="senden">
	<input type="reset" value="löschen">
</form>

</body>
</html>