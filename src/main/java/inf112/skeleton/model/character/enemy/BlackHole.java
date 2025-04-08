package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.character.Character;

import inf112.skeleton.model.character.CharacterAttributes;

public class BlackHole extends SimpleEnemy implements iMovingEnemy {

    private boolean isMoving;

    public BlackHole(Character blackHole) {
        super(blackHole);
        this.isMoving = true;
    }

    public BlackHole(String name, CharacterAttributes attributes, Vector2 size, World world, Character target) {
        this(new Character(name, attributes, size, world));
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(enemyCharacter.getAttributes().getStrength());
    }

    public Character getEnemyCharacter() {
        return this.enemyCharacter;
    }

    @Override
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }
}
