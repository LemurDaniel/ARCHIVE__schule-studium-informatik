<?php
include 'Daten.php';

      $msql = new mysqli('localhost', 'root', '', 'bistro' );

      if ($msql->connect_errno) {
          echo "No Connection to Database. Please try again later: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
      }

      $num = $_POST['artNummer'];
      $bez = $_POST['bezeichnung'];
      $bes = $_POST['beschreibung'];
      $gru = $_POST['gruppe'];
      $meng = $_POST['menge'];
      $eig = $_POST['eigenschaft'];
      $preis = $_POST['preis'];

      $sql = "insert into menue (ArtNummer, ArtBezeichnung, ArtBeschreibung, ArtGruppe, Menge, Eigenschaft, Preis) values('$num','$bez','$bes','$gru','$meng','$eig','$preis')";
      if(!$msql->query($sql)){
          $fehler = "<br><br> <h1>";
          $fehler .= "<br>".$msql->error;
          $fehler .= "<br>!!!Schreibfehler!!!";
          $fehler .= "<br>Fehlernummer: ".$msql->errno;
          $fehler .= "<br>DBSTATE: ".$msql->sqlstate;
          $fehler .= "</h1>";
      }else $fehler = "Der Artikel wurde in die Datenbank aufgenommen";

      echo getHtml()->saveHTML();
      echo $fehler;
?>
