<?php 
if(!isset($_SESSION["name"])){
	session_destroy();
	echo "<p>Kein Zugang</p>";
	echo "<p><a href='index.php'>Zum Login</a></p>";
	echo "</body></html>";
	exit();
}
?>