# INF112 Project - _Star Jump_

## Team: **DreamTeam** (Gruppe 7):

- Maren A. I. Gundhus, developer + test consultant
- Åse Fagerbakke, public relations + developer
- Jonas Justesen, developer
- Mohamad Al-Mozzen, designer + developer
- Thora Moen, developer + secretary
- Anna Sviland, developer

* Trello: [trello](https://trello.com/b/g0zg5tGH/inf112)
* Meeting minutes: [møtereferater](doc/meetingMinutes.md)

## About the game

- A star has fallen from the sky! Help it jump back home — but beware of black holes and cannons, they're fatal! Use the arrow \* keys to move side to side, and press the spacebar to jump. Along the way, you can collect power-ups (rainbows) to gain a
- short burst of flight. You can also grab diamonds to increase your score. Stay alert, even with the power-up effect, black \* holes are still dangerous!

## How to play

- Compile with mvn package.
- You can run the project with Maven using `mvn exec:java`
- If needed you can first compile with `mvn compile` – or in a single step, `mvn compile exec:java`.
- Requires Java 21 or later

## Credits

Graphichs

- Background: https://opengameart.org/content/dirt-platformer-tiles. (Open license.)
- Tiles: https://opengameart.org/content/additions-to-32x32-fantasy-tileset. (Open license.)
- Background: https://incolgames.itch.io/dungeon-platformer-tile-set-pixel-art.
  - Created by David G.
  - Used with permission, credit appreciated.
- Star/player: Created by Maren A. I. Gundhus.
- Enemy: Created by Mohamad Al-Mozzen.
- PowerUp: Created by Åse Fagerbakke.
- Diamond: Created by Åse Fagerbakke.

Sounds

- Sound for Powerup: Created by Åse Fagerbakke

## Testing

Run unit tests with mvn test – unit test files should have Test in the file name, e.g., ExampleTest.java. This will also generate a JaCoCo code coverage report, which you can find in target/site/jacoco/index.html.
Use mvn verify to run integration tests, if you have any. This will do everything up to and including mvn package, and then run all the tests with IT in the name, e.g., ExampleIT.java.

## Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

- target/NAME-VERSION.jar – your compiled project, packaged in a JAR file
- target/NAME-VERSION-fat.jar – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/NAME-VERSION-fat.jar`.
If you have test failures, and really need to build a jar anyway, you can skip testing with `mvn -Dmaven.test.skip=true package`.
