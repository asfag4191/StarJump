package inf112.skeleton.model.game_objects.projectile;

import com.badlogic.gdx.math.Vector2;

public record ProjectileAttributes(Vector2 velocity, float range, float damage, boolean gravity) {
}
