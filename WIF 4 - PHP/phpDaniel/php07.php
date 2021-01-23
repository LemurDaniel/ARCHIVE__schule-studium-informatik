<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>PHP7 Bild hochladen und anzeigen</title>
</head>

<body>

<?php 
	if(isset($_FILES['fupload'])){
		print "Name: ".$_FILES['fupload']['name']."<br>";
		print "Size: ".$_FILES['fupload']['size']." Bytes<br>";
		print "temp name: ".$_FILES['fupload']['tmp_name']."<br>";
		print "Typ: ".$_FILES['fupload']['type']."<br>";
		print "error: ".$_FILES['fupload']['error']."<br>";
		$source=$_FILES['fupload']['tmp_name'];
		$target="D://xampp/htdocs/phpDaniel/".$_FILES['fupload']['name'];
		
		$bildueberhost="http://localhost/phpDaniel/".$_FILES['fupload']['name'];
		
			move_uploaded_file($source, $target) or die ("Fehler beim kopieren");
		
		$size=getimagesize($target); //Abfrage ob es sich um ein Bild handelt
		if ($size[2]==1 or $size[2]==2 or $size[2]==3) {//2, 3, 1; jpg, png, gif
			$imgstr="<p><img src=\"$bildueberhost\" alt=\"hochgeladenes Bild\"></p>";
			print $imgstr;
		}
	}
?>

	<form enctype="multipart/form-data" action="<?php print($_SERVER['PHP_SELF'])?>" method="post">	
		<br>
		<input type="file" name="fupload" ></input>
		<input type="submit" value="Hochladen"></input>
		
	</form>
</body>
</html>