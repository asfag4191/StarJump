package inf112.skeleton.model.items.powerup;

import java.util.EnumMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.model.character.Character;


/**
 * A provider class responsible for registering and retrieving factories that create different power-up types.
 *
 * This class supports dynamic registration of new power-up types without modifying existing provider logic.</p>
 */
public final class PowerUpFactoryProvider {

    private final EnumMap<PowerUpEnum, PowerUpFactory> factories = new EnumMap<>(PowerUpEnum.class);

    /**
     * Initializes the provider by registering default power-up factories.
     */
    public PowerUpFactoryProvider(boolean registerDefaults) {
        if (registerDefaults) {
            register(PowerUpEnum.FLYING, "map/tilemaps/tilesets/rainbow16.png",
                (character, position, sprite) -> new FlyingPowerUp(character, position, sprite));
    
            register(PowerUpEnum.DIAMOND, "map/tilemaps/tilesets/Diamond.png",
                (character, position, sprite) -> new DiamondPowerUp(character, position, sprite));
        }
    }

    /**
     * Creates a provider with default power-ups registered.
     */
    public PowerUpFactoryProvider() {
        this(true); 
    }

    /**
     * Registers a new power-up type with its corresponding texture and creation logic.
     *
     * @param type         The specific type of power-up to register (e.g., {@code FLYING}, {@code DIAMOND}).
     * @param texturePath  The path to the image asset representing the power-up.
     * @param creator      The creation logic defining how instances of this power-up type are instantiated.
     */
    public void register(PowerUpEnum type, String texturePath, PowerUpCreator creator) {
        factories.put(type, new PowerUpFactory(texturePath) {
            @Override
            public iPowerUp create(Character character, Vector2 position) {
                return creator.create(character, position, createSprite(position));
            }
        });
    }


    /**
     * Retrieves the registered factory for a given power-up type.
     *
     * @param type The type of power-up to retrieve the factory for.
     * @return The {@link PowerUpFactory} associated with the given power-up type.
     * @throws IllegalArgumentException if no factory is registered for the specified type.
     */
    public PowerUpFactory getFactory(PowerUpEnum type) {
        PowerUpFactory factory = factories.get(type);
        if (factory == null)
            throw new IllegalArgumentException("Factory not registered: " + type);
        return factory;
    }

    /**
     * A functional interface defining the logic for creating power-up instances.
     */
    public interface PowerUpCreator {

        /**
         * Creates an instance of a specific power-up.
         *
         * @param character The player character who can collect or interact with the power-up.
         * @param position  The world position where the power-up will appear.
         * @param sprite    The graphical representation of the power-up.
         * @return An instance of {@link iPowerUp}.
         */
        iPowerUp create(Character character, Vector2 position, Sprite sprite);
    }
}