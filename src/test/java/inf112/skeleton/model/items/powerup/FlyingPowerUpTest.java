// package inf112.skeleton.model.items.powerup;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.math.Vector2;

// public class FlyingPowerUpTest {

//     // Your custom test player implementation
//     static class TestPlayer {
//         // Track the state we want to test
//         public float gravityScale = 1f;
//         public boolean collisionEnabled = true;
//         public Vector2 linearVelocity = new Vector2(0, 0);
        
//         // Implement ONLY the methods FlyingPowerUp actually uses
//         public Object getBody() {
//             return new Object() {
//                 public void setGravityScale(float scale) {
//                     gravityScale = scale;
//                 }
                
//                 public float getGravityScale() {
//                     return gravityScale;
//                 }
                
//                 public Vector2 getLinearVelocity() {
//                     return linearVelocity;
//                 }
                
//                 public void setLinearVelocity(float x, float y) {
//                     linearVelocity.set(x, y);
//                 }
//             };
//         }
        
//         public void setCollisionEnabled(boolean enabled) {
//             collisionEnabled = enabled;
//         }
//     }

//     @Test
//     public void testPowerUpEffects() {
//         // Setup
//         TestPlayer dummy = new TestPlayer();
//         //FlyingPowerUp powerUp = new FlyingPowerUp(dummy, new Vector2(0, 0), null);
        
//         // Execute
//         //powerUp.applyPowerUpEffect();
        
//         // Verify
//         assertEquals(0f, dummy.gravityScale, "Gravity should be disabled");
//         assertEquals(5f, dummy.linearVelocity.y, "Should get upward boost");
//         assertFalse(dummy.collisionEnabled, "Collision should be off");
//     }
// }
