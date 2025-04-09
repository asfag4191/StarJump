package inf112.skeleton.model.items.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;

public class PowerUpFactoryProviderTest {

    private PowerUpFactoryProvider provider;
    private Character characterStub;

     @BeforeAll
    static void initLibGdx() {
        if (Gdx.app == null) {
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new com.badlogic.gdx.ApplicationAdapter(){}, config);
        }
    }
    @BeforeEach
    public void setUp() {
        Gdx.gl = Gdx.gl20 = Mockito.mock(com.badlogic.gdx.graphics.GL20.class);
        provider = new PowerUpFactoryProvider();

        // Simple stub for Character (no mocking library)
        CharacterAttributes attributes = new CharacterAttributes(100.0f, 10.0f, 2, 1.0f, 0.5f);
        Vector2 size = new Vector2(1, 2);
        World worldStub = new World(new Vector2(0, -9.81f), true);
        characterStub = new Character("TestCharacter", attributes, size, worldStub);
    }

    @Test
    public void testFactoryRetrievalForRegisteredTypes() {
        PowerUpFactory flyingFactory = provider.getFactory(PowerUpEnum.FLYING);
        assertNotNull(flyingFactory, "Factory for FLYING should not be null");

        PowerUpFactory diamondFactory = provider.getFactory(PowerUpEnum.DIAMOND);
        assertNotNull(diamondFactory, "Factory for DIAMOND should not be null");
    }

    @Test
    public void testPowerUpCreationUsingFactory() {
        Vector2 position = new Vector2(10, 10);

        PowerUpFactory flyingFactory = provider.getFactory(PowerUpEnum.FLYING);
        iPowerUp flyingPowerUp = flyingFactory.create(characterStub, position);
        assertTrue(flyingPowerUp instanceof FlyingPowerUp, "Should create a FlyingPowerUp instance");

        PowerUpFactory diamondFactory = provider.getFactory(PowerUpEnum.DIAMOND);
        iPowerUp diamondPowerUp = diamondFactory.create(characterStub, position);
        assertTrue(diamondPowerUp instanceof DiamondPowerUp, "Should create a DiamondPowerUp instance");
    }

    @Test
    public void testExceptionForUnregisteredType() {
        PowerUpFactoryProvider provider = new PowerUpFactoryProvider(false); // don't register FLYING/DIAMOND
    
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            provider.getFactory(PowerUpEnum.FLYING); // Not registered!
        });
    
        assertEquals("Factory not registered: FLYING", exception.getMessage());
    }

   // @Test
   // public void testOverwriteExistingFactoryDynamically() {
    //    provider.register(PowerUpEnum.FLYING, "path/to/flying.png",
     //           (character, position, sprite) -> new FlyingPowerUp(character, position, sprite));
    
    //    PowerUpFactory updatedFactory = provider.getFactory(PowerUpEnum.FLYING);
     //   assertNotNull(updatedFactory, "Factory for FLYING should still exist");
    
     //   iPowerUp updatedPowerUp = updatedFactory.create(characterStub, new Vector2(5, 5));
     //   assertTrue(updatedPowerUp instanceof FlyingPowerUp, "Should create FlyingPowerUp after dynamic overwrite");
   // }
}
