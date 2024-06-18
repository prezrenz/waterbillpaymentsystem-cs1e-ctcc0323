set shell := ["powershell.exe", "-c"]

all: compile run

compile:
    mvn package

run:
    java -jar target/waterbillpaymentsystem-1.0-SNAPSHOT.jar
