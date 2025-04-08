package inf112.skeleton.model.character.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;

public class EnemyFactory {

    private World world;

    public EnemyFactory(World world) {
        this.world = world;
    }

    /**
     * 
     * @return next enemy to spawn
     */
    public BlackHole getNextEnemy() {
        CharacterAttributes attributes = new CharacterAttributes(
            1,
            1,
            0,
            3.5f,
            1
        );

        Character character = new Character("black hole", attributes, new Vector2(1,1), world);
        
        // TODO: what position should the new black hole have?
        character.setPosition(new Vector2(15, 3));

        BlackHole blackHole = new BlackHole(character);
        return blackHole;
    }
}
