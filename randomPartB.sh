# Author - Louis
# Be sure to run the server before this, using "java -jar runTodoManagerRestAPI-1.5.5.jar"
# For some reason mvn test doesn't work with cucumber flags, hence why had to use exec:java directly
mvn clean install -DskipTests \
exec:java -Dexec.classpathScope=test \
-Dexec.mainClass=io.cucumber.core.cli.Main \
-Dcucumber.glue="com.ecse429.autoproj8.partB.steps" \
-Dcucumber.execution.order="random" \
-Dcucumber.features="src/test/java/com/ecse429/autoproj8/partB/gherkin/" \
-Dcucumber.plugin="pretty"