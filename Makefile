compile :
	javac -cp "lib/*" -d out src/model/*.java src/mapper/*.java src/repository/*.java src/service/*.java src/Main.java

run :
	java -cp "out;lib/*" Main