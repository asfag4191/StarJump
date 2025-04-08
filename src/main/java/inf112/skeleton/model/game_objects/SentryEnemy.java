package inf112.skeleton.model.game_objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.controllable_characters.Player;

class SentryEnemy extends SimpleEnemy implements iStationaryEnemy {
    private Player player;
    public Vector2 playerDirection;
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public SentryEnemy(Character character, Player player) {
        super(character);
        this.player = player;
    }

    @Override
    public void shoot(Vector2 direction, float bulletSpeed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shoot'");
    }

    @Override
    public boolean seesTarget(Vector2 targetPosition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seesTarget'");
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.playerDirection = getPlayerDirection();
    }

    private Vector2 getPlayerDirection() {
        Vector2 playerPosition = this.player.character.getPosition();
        Vector2 enemyPosition = character.getPosition();

        return playerPosition.sub(enemyPosition).nor();
    }

}
