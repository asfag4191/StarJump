package inf112.skeleton.model.character.controllable_characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.controller.InputBinder;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;

public class Player {
    public final Character character;
    public final InputBinder controller = new InputBinder();

    public Player(Character character) {
        this.character = character;
        this.character.setPlayer(true);
    }

    public Player(String name, CharacterAttributes attributes, Vector2 size, World world) {
        this(new Character(name, attributes, size, world));
    }
}
