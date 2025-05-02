package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.enemy.projectile.Projectile;

public class ProjectileCollisionHandler implements CollisionHandler {

    @Override
    public void onContactBegin(Contact contact, Fixture fixA, Fixture fixB) {
        targetCollision(fixA, fixB);
    }

    @Override
    public void onContactEnded(Contact contact, Fixture fixA, Fixture fixB) {
        // Not needed for now
    }

    /**
     * Handles collisions between an Enemy Character and another Character,
     * including black hole top sensor contact.
     */
    private void targetCollision(Fixture fixA, Fixture fixB) {
        // Regular black hole attack logic (side collision etc)
        Object userDataA = fixA.getBody().getUserData();
        Object userDataB = fixB.getBody().getUserData();

        if ("ground".equals(userDataA) && userDataB instanceof Projectile) {
            Projectile proj = (Projectile) userDataB;
            proj.dispose();
        } else if ("ground".equals(userDataB) && userDataA instanceof Projectile) {
            Projectile proj = (Projectile) userDataA;
            proj.dispose();
        }

        if (userDataA instanceof Character && userDataB instanceof Projectile) {
            Character character = (Character) userDataA;
            Projectile projectile = (Projectile) userDataB;
            if (character.isPlayer()) {
                character.takeDamage(projectile.getAttributes().damage());
                projectile.dispose();
            }
        } else if (userDataA instanceof Projectile && userDataB instanceof Character) {
            Character character = (Character) userDataB;
            Projectile projectile = (Projectile) userDataA;
            if (character.isPlayer()) {
                character.takeDamage(projectile.getAttributes().damage());
                projectile.dispose();
            }
        }

    }
}
