# Readme

The console app will parse  html code from the url an return a JSON Array of all products on the page. That app will have test cases included.


# Requirements

- maven (version)
- java 1.7, set JAVA_HOME
- JUnit
- jsoup
- json-simple
- jackson-mapper-asl
- commons-io


Requirements will be pulled by maven


### compile run and test the app

cd my-test-app-v3

mvn assembly:assembly

java -cp target/my-test-app-1.0-SNAPSHOT.jar co.uk.sainsburys.app.App

mvn test

### create Documentation 

-mnv site

docs can be found as html in target/site/index.html 
