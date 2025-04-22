package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.view.screen.GameScreen;

public class EnemyFactory {

    private GameScreen screen;
    private static final Texture sentryEnemyTexture = new Texture(Gdx.files.internal("sprites/star.png"));
    private static final Texture blackHoleTexture = new Texture(Gdx.files.internal("sprites/simple_blackhole.png"));

    public EnemyFactory(GameScreen screen) {
        this.screen = screen;
    }

    /**
     * 
     * @return next <code>BlackHole</code> enemy
     */
    public SimpleEnemy getNextBlackHole() {
        SimpleEnemy blackHole = createBlackHole();
        return blackHole;
    }

    /**
     * 
     * @return next <code>SentryEnemy</code>
     */
    public SimpleEnemy getNextSentryEnemy() {
        SimpleEnemy sentryEnemy = createSentryEnemy();
        return sentryEnemy;
    }

    // TODO: positions for BlackHole!
    // possible solution: have list of possible positions, choose one pos at random
    // possible solution: have Queue of possible positions, take next pos from queue
    private SimpleEnemy createBlackHole() {
        CharacterAttributes attributes = weakEnemyAttributes();
        Character character = new Character("black hole", attributes, new Vector2(1,1), screen.getWorld());

        character.setPosition(new Vector2(15, 3)); // TODO

        SimpleEnemy blackHole = new BlackHole(character);

        blackHole.enemyCharacter.animator.addAnimation("idle", blackHoleTexture, 1, 1, 0);
        blackHole.enemyCharacter.animator.play("idle");

        return blackHole;
    }

    // TODO: positions for SentryEnemy!
    // possible solution: have list of possible positions, choose one pos at random
    // possible solution: have Queue of possible positions, take next pos from queue
    private SimpleEnemy createSentryEnemy() {
        CharacterAttributes attributes = strongEnemyAttributes();

        Character character = new Character("enemy", attributes, new Vector2(1, 1), screen.getWorld());
        character.setPosition(new Vector2(3, 4)); // TODO

        SimpleEnemy sentryEnemy = new SentryEnemy(character, screen.getPlayer());
        sentryEnemy.enemyCharacter.animator.addAnimation("idle", sentryEnemyTexture, 1, 1, 0);
        sentryEnemy.enemyCharacter.animator.play("idle");

        return sentryEnemy;
    }

    private CharacterAttributes weakEnemyAttributes() {
        return new CharacterAttributes(
            1,
            1,
            0,
            3.5f,
            1
        );
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
