<?php 
	header("Content-type: image/jpeg");
	$x = 1000;
	$y = 1000;
	$a = 25;
	$img=imagecreatetruecolor($x, $y); // Grafikobjekt
	// Rechteck zeichnen
	$php_blau = imagecolorallocate($img, 119, 123, 180);
	$schwarz = imagecolorallocate($img, 0, 0, 0);
	$p1 = imagecolorallocate($img, 255, 240, 0);
	$p2 = imagecolorallocate($img, 255, 0, 240);
	
	imagefilledrectangle($img, 0, 0, $x, $y, $php_blau);

	//Fill Pixel
	for($i=$a; $i<=$y-$a; $i++) for ($j=$a; $j<=$x-$a; $j+=5) imagesetpixel($img, $i, $j, imagecolorallocate($img, $i, $j, $i+$j));

	//Image Lines
	$style = array($p1, $p1, $p1, $p1, $p2, $p2);
	imagesetstyle($img, $style);
	imagesetthickness($img, 5);
	imageline($img, $x, $y, 0, 0, IMG_COLOR_STYLED);
	imagedashedline($img, 0, $y, $x, 0, IMG_COLOR_STYLED);

	imagejpeg($img, null, 100);
?>
