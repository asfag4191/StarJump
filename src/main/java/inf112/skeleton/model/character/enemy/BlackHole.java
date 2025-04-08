package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.model.character.Character;

import inf112.skeleton.model.character.CharacterAttributes;

public class BlackHole implements IEnemy {

    private final Character blackHole;

    public BlackHole(Character blackHole) {
        this.blackHole = blackHole;
    }

    public BlackHole(String name, CharacterAttributes attributes, Vector2 size, World world, Character target) {
        this(new Character(name, attributes, size, world));
    }

    @Override
    public void attack(Character target) {
        target.takeDamage(blackHole.getAttributes().getStrength());
    }

    public Character getEnemyCharacter() {
        return this.blackHole;
    }
}
