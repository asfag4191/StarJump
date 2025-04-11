package inf112.skeleton.model.character.enemy.projectile;

import com.badlogic.gdx.math.Vector2;

public record ProjectileAttributes(Vector2 velocity, float range, float damage, boolean gravity) {
}
