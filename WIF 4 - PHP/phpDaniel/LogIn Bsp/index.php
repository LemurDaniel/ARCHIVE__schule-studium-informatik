<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Log In Seite - Session</title>
<style>
	label{
		display: block;
		float: left;
		width: 60px;
	}
	input{
		display: block;
		float: none;
		text-align: center;
		width: 160px;
	}
	</style>
</head>

<body>
<?php 
	session_start();
	session_destroy();
	$_SESSION[]=array();
?>

<h1>Login: </h1><br>
<form action="intro.php" method="post">
	<input type="text" name="name" id="name" placeholder="Name">
	<input type="password" name="pw" id="pw" placeholder="Passwort">
	<input type="submit" name="passwort" value="Log In">
</form>
</body>
</html>