<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Formular an ein PHP Skript senden und dort auswerten</title>
</head>

<body>

<h1>Formular an ein PHP Skript senden und dort auswerten</h1>
Bitte geben Sie Daten ein und senden ein Formular ab<br><br>

<form id="form1" name="form1" method="GET" action="php5Auswertung.php">
	<br>Name:<input type="text" name="nachname">
	<br>Vorname:<input type="text" name="vorname">
	<br>Ort:<input type="text" name="ort">
	<br><input type="submit" value="senden">
	<input type="reset" value="löschen">
</form>

</body>
</html>