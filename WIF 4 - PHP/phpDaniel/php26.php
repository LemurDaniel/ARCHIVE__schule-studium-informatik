<?php 
	header("Content-type: image/jpeg");
	$x = 1000;
	$y = 1000;
	$img=imagecreatetruecolor($x, $y); // Grafikobjekt
	// Rechteck zeichnen
	$php_blau = imagecolorallocate($img, 119, 123, 180);
	imagefilledrectangle($img, 0, 0, $x, $y, $php_blau);
	// schwarzer Kreis
	$schwarz = imagecolorallocate($img, 0, 0, 0);
	imagefilledellipse($img, $x/2, $y/2, $x/2, $y/2, $schwarz);
	
	// schwarzer Kreis
	$rot = imagecolorallocate($img, 255, 255, 0);
	imagefilledarc($img, $x/2, $y/2, $x/3, $y/3, 45, 315, $rot, 0);
	imagefilledarc($img, $x/2, $y/2.5, $x/24, $y/24, 20, 340, $schwarz, 0);

	imagejpeg($img, null, 100);
?>
