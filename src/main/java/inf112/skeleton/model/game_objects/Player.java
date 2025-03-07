package inf112.skeleton.model.game_objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.controller.InputBinder;
import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.Stats;

public class Player{
    public final Character character;
    public final InputBinder controller = new InputBinder();

    public Player(Character character) {
        this.character = character;
    }

    public Player(String name, Stats stats, Vector2 size, World world) {
        this(new Character(name, stats, size, world));
    }
}
