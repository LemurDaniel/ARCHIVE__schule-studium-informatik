

<?php

      $msql = new mysqli('localhost', 'root', '', 'LandauDesign' );

      if ($msql->connect_errno) {
          echo "No Connection to Database. Please try again later: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
      }

      $date = "".date("H:i:s d.m.Y");
      $email = $_POST['email'];


      $sql = "insert into Newssubscriber(email, date) values('$email', '$date')";

      if($msql->query("$sql")){
        $message = "Thanks for subscribing to our Newsletter: ";
      }else{
        $message = "Unkown Error, Please Try again later<br>";
       if($msql->errno == 1062){
         $message="You've already subscribed with this email-adress: ";
        }
      }


      $html = new DOMDocument();
      $html->loadHTMLFile($_SERVER['HTTP_REFERER']);
      ob_end_clean ();
      $news = $html->getElementById("newsletter");
      foreach ($news->getElementsByTagName("*") as $element) {

          if($element->tagName == "h1") {
            $element->nodeValue = $message;
            $new = $html->CreateElement("span", $email);
            $new->setAttribute("class", "highlight");
            $element->appendChild($new);
          }
          if($element->tagName == "form") $element->parentNode->removeChild($element);
      }

    $html->saveHTMLFILE("../temp.html");
    header("Location: http://localhost/Projekt2/temp.html");
?>
