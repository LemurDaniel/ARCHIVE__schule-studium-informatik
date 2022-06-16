# Informatik-Gymnasium
Informatik Zeug aus dem Informatik Unterricht im Gymnasium und Studium

<h3> Quick commands lookup </h3>

	javac -d . ../src/<package>/*.java
	javac -d . ../src/<package>/*.java ../src/<package2>/*.java
	
   <h6>Mit Libraries</h6>
	
	javac -cp ".;../../../lib/*" -d . ../src/<package>/*.java
	javac -cp ".;../../../lib/*" -d . ../src/<package>/*.java ../src/<package2>/*.java

	
	java -cp ".;../../../lib/*"  <package>.<Klasse Mit Main Methode>
		
	javac -d ./bin -cp ".;../lib/*" --source-path ./src ./src/application/Main.java	
