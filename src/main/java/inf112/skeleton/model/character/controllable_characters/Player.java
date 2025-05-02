package inf112.skeleton.model.character.controllable_characters;

import inf112.skeleton.controller.InputBinder;
import inf112.skeleton.model.character.Character;

public class Player {
    public final Character character;
    public InputBinder controller = new InputBinder();

    public Player(Character character) {
        this.character = character;
        this.character.setPlayer(true);
    }
}
