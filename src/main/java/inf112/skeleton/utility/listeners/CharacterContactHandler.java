package inf112.skeleton.utility.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.app.StarJump;
import inf112.skeleton.model.character.Character;

public class CharacterContactHandler implements CollisionHandler {

    @Override
    public void onContactBegin(Contact contact, Fixture fixA, Fixture fixB) {
        groundCollision(fixA, fixB, true);
    }

    @Override
    public void onContactEnded(Contact contact, Fixture fixA, Fixture fixB) {
        groundCollision(fixA, fixB, false);
    }

    private void groundCollision(Fixture fixA, Fixture fixB, boolean isGrounded) {
        boolean isSensorA = fixA.getFilterData().categoryBits == StarJump.GROUND_SENSOR_BIT;
        boolean isSensorB = fixB.getFilterData().categoryBits == StarJump.GROUND_SENSOR_BIT;
        boolean isGroundA = fixA.getFilterData().categoryBits == StarJump.GROUND_BIT;
        boolean isGroundB = fixB.getFilterData().categoryBits == StarJump.GROUND_BIT;
    
        Object userDataA = fixA.getBody().getUserData();
        Object userDataB = fixB.getBody().getUserData();
    
        Character charac = null;
    
        if (isSensorA && isGroundB) {
            charac = extractCharacter(userDataA);
        } else if (isSensorB && isGroundA) {
            charac = extractCharacter(userDataB);
        }
    
        if (charac != null) {
            charac.setGrounded(isGrounded);
        }
    }

    private Character extractCharacter(Object userData) {
        if (userData instanceof Character character) {
            return character;
        }
        if (userData instanceof inf112.skeleton.model.character.enemy.BlackHole bh) {
            return bh.getCharacter(); 
        }
        return null;
    }
}
