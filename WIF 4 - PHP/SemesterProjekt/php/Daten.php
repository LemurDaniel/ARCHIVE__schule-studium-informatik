<?php
function getHtml(){
      $msql = new mysqli('localhost', 'root', '', 'bistro' );

      if ($msql->connect_errno) {
          echo "No Connection to Database. Please try again later: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
      }


      $sql = "Select * from menue";
      $result = $msql->query($sql);
      $tabRow = "";
      while($row = $result->fetch_array(MYSQLI_NUM)){
        $tabRow .= "<tr>";
        foreach ($row as $element) $tabRow .= "<td><span>$element</span></td>";
        $tabRow .= "</tr>";
      }

      $html = new DOMDocument();
      $html->loadHTMLFile("../html/DisplayMenue.html");


    $content = $html->createElement("tbody");
    $speisekarte = $html->getElementById("speisekarte");
    $speisekarte->appendChild($content);

    $tpl = new DOMDocument;
    $tpl->loadHTML($tabRow);
    $content->appendChild($html->importNode($tpl->documentElement, TRUE));

      ob_end_clean ();
    return $html;
  }
?>
