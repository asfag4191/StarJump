# INF112 Project - *Star Jump*

## Team: **DreamTeam** (Gruppe 7): 
- Maren A. I. Gundhus, developer + test consultant
- Åse Fagerbakke, public relations + developer
- Jonas Justesen, developer
- Mohamad Al-Mozzen, designer
- Thora Moen, developer
- Anna Sviland, developer

* Trello: [trello](https://trello.com/b/g0zg5tGH/inf112)
* Meeting minutes: [møtereferater](doc/meetingMinutes.md)


## About the game
*"A star has fallen from the sky! Help it to jump back to its home. But beware of black holes! They are fatal."*

## How to play
**todo**

## Running
You can run the project with Maven using `mvn exec:java`.

You may have to compile first, with `mvn compile` – or in a single step, `mvn compile exec:java`.

## Testing
Run unit tests with mvn test – unit test files should have Test in the file name, e.g., ExampleTest.java. This will also generate a JaCoCo code coverage report, which you can find in target/site/jacoco/index.html.
Use mvn verify to run integration tests, if you have any. This will do everything up to and including mvn package, and then run all the tests with IT in the name, e.g., ExampleIT.java.

## Jar Files
If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:
- target/NAME-VERSION.jar – your compiled project, packaged in a JAR file
- target/NAME-VERSION-fat.jar – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/NAME-VERSION-fat.jar`.
If you have test failures, and really need to build a jar anyway, you can skip testing with `mvn -Dmaven.test.skip=true package`.

# Credits

**todo**: informasjon om hvor grafikk/lyd er hentet fra (kilde/opphavsrett)



