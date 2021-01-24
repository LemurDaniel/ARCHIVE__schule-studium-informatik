# Informatik-Gymnasium
Informatik Zeug aus dem Informatik Unterricht im Gymnasium und Studium

<h3> 1. Website </h3>
Die Website ist eine einfache statische Website

<h3> 2. Java Schule </h3>

Die Java Projekte sind mit BlueJ entstanden,
dementsprechend können sie über BlueJ ausgeführt werden.

Mit BlueJ können die Methoden von Instanzen angesprochen werden,
über die Konsole ist dies nicht möglich.

<h4> Über Konsole starten </h4>

<h6>1. Mit CMD in das Verzeichnis gehen</h6>
	
	CD "UEBUNG - Auto"
	CD <Folder>

<h6>2. Kompilieren</h6>
	
	javac -d ./bin ./*.java
	
<h6>3. Enter Bin</h6>
	
	CD bin
	
<h6>4. Ausführen der Hauptklasse (meistens RUN)</h6>
	
	java <Class-Datei mit Main-Methode>
	java RUN
	
<h6>5. Rekompilieren von bin-Ordner aus<br>
	<i>Bei Projekt Mario müssen die pics im selben Verzeichnis sein wie die class-Dateien, also im bin</i></h6>
	
	javac -d . ../*.java

	

<h3> 3. Java Studium </h3>

<h6>1. Jeder Workspace zb. HOME beinhaltet mehrere Projekte</h6>

<h6>2. In Jedem Projekt ist der Sourcecode nach Aufgaben in mehrere Packages unterteilt</h6>

<h6>4. In bin Ordner des Projekts gehen</h6>

<h6>3. Kompilieren vom Bin Ordner aus</h6>
	
	javac -d . ../src/<package>/*.java<br>
	javac -d . ../src/<package>/*.java ../src/<package2>/*.java
	
   <h6>Mit Libraries</h6>
	
	javac -cp ".;../../../lib/*" -d . ../src/<package>/*.java<br>
	javac -cp ".;../../../lib/*" -d . ../src/<package>/*.java ../src/<package2>/*.java

<h6>4. Ressourcen im src, müssen in bin vorhanden sein: css, fxml, etc.	</h6>

<h6>5. Ausführen</h6>
	
	java <package>.<Klasse Mit Main Methode>
	
<h6>Mit Libraries</h6>
	
	java -cp ".;../../../lib/*"  <package>.<Klasse Mit Main Methode>
		
<h6>6. Java 1.8 und 10 mit JavaFX</h6>

	OneDrive\WIF - Bachelor\Studium\WIF 3\Modellgetriebene Softwareentwicklung\Praxisteil 1
	https://www.oracle.com/de/java/technologies/javase/javase-jdk8-downloads.html
	
	
<h3> 4. WIF 3 - Projekt FilmDB </h3>

	Filmdatenbank as Parentdirectory !

<h6>1.  Kompilliere mit jdk 10.0.2 (JavaFX noch inbegriffen)>br>
	<i>Vorher die jdk rausnehmen und Umgebungsvariable anpassen </i></h6>

	javac -d ./bin -cp ".;./lib/*" --source-path ./src ./src/application/Main.java	

	oder

	javac -d ./bin -cp ".;./lib/bcrypt-0.6.0-optimized.jar;./lib/bytes-0.8.0.jar;./lib/controlsfx-9.0.0.jar;./lib/guava-26.0-jre.jar;./lib/mssql-jdbc-7.0.0.jre10.jar --source-path ./src ./src/application/Main.java


<h6>2.  Asset Files Kopieren</h6>

	Wenn nicht vorhanden: Kopiere /css;/fxml;/images from /src/gui to /bin/gui !!!


<h6>3.  Enter bin directory</h6>

	cd /bin 


<h6>4. Run Java mit jdk 10.0.2 plus classpath</h6>

	java -cp ".;../lib/*" application.Main


	(if upper doesn't work)

	java -cp ".;./lib/bcrypt-0.6.0-optimized.jar;./lib/bytes-0.8.0.jar;./lib/controlsfx-9.0.0.jar;./lib/guava-26.0-jre.jar;./lib/mssql-jdbc-7.0.0.jre10.jar"  application.Main


<h6>5. Libs sind alle an einen Ort verschoben </h6>

	Classpath von "WIF 3 - ProjektFilmDB\1 - Endabgabe\Final":
	-cp ".;./lib/*"
	Classpath von "WIF 3 - ProjektFilmDB\1 - Endabgabe\Final\bin":
	-cp ".;../lib/*"
	
	Classpath von "WIF 3 - ProjektFilmDB\1 - Latest\":
	-cp ".;../lib/*"
	Classpath von "WIF 3 - ProjektFilmDB\1 - Latest\bin":
	-cp ".;../../lib/*"
	
	Classpath von "WIF 3 - ProjektFilmDB\Backup History\<Any Folder>":
	-cp ".;../../../lib/*"
	Classpath von "WIF 3 - ProjektFilmDB\Backup History\<Any Folder>\bin":
	-cp ".;../../../../lib/*"
	
	Hinweise:
	Der Connection String im DB-Manager muss angepasst werden
	und dann rekompiliert
	
	
