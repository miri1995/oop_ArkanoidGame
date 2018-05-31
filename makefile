#Miri Asher
#205891492

compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar:. -d bin @sources.txt	

run:
	java -cp biuoop-1.4.jar:bin:resources Ass6Game
	
bin:
	mkdir bin

jar:
	jar cfm ass6game.jar Manifest.mf -C bin . -C resources .