package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.view.screen.GameScreen;

class EnemyFactory {

    private GameScreen screen;

    public EnemyFactory(GameScreen screen) {
        this.screen = screen;
    }

    /**
     * 
     * @return next <code>BlackHole</code> enemy
     */
    public SimpleEnemy getNextBlackHole(Vector2 position) {
        SimpleEnemy blackHole = createBlackHole(position);
        return blackHole;
    }

    /**
     * 
     * @return next <code>SentryEnemy</code>
     */
    public SimpleEnemy getNextSentryEnemy(Vector2 position) {
        SimpleEnemy sentryEnemy = createSentryEnemy(position);
        return sentryEnemy;
    }

    private SimpleEnemy createBlackHole(Vector2 position) {
        CharacterAttributes attributes = weakEnemyAttributes();
        Character character = new Character("black hole", attributes, new Vector2(1, 1), screen.getWorld());
        character.setPosition(position);

        return new BlackHole(character, screen.getWorld());
    }

    private SimpleEnemy createSentryEnemy(Vector2 position) {
        CharacterAttributes attributes = strongEnemyAttributes();

        Character character = new Character("enemy", attributes, new Vector2(1, 1), screen.getWorld());
        character.setPosition(position);
        SimpleEnemy sentryEnemy = new SentryEnemy(character, screen.getPlayer(), screen.getWorldModel());

        return sentryEnemy;
    }

    private CharacterAttributes weakEnemyAttributes() {
        return new CharacterAttributes(
                1,
                1,
                0,
                3.5f,
                1);
    }

    private CharacterAttributes strongEnemyAttributes() {
        return new CharacterAttributes(
                6,
                1,
                0,
                3.5f,
                5);
    }

}
