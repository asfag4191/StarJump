package inf112.skeleton.model.game_objects;

import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.view.Animator;

class ShootingProjectile {
    private final float speed;
    private final float damage;
    private final float range;
    private Animator animator;

    public ShootingProjectile(float speed, float damage, float range, Animator animator) {
        this.speed = speed;
        this.damage = damage;
        this.range = range;
        this.animator = animator;
    }

}
