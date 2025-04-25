package inf112.skeleton.model.items.door;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.model.character.Character;
import inf112.skeleton.model.character.CharacterAttributes;
import inf112.skeleton.model.character.controllable_characters.Player;
import inf112.skeleton.view.screen.GameScreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DoorObjectTest {

    private GameScreen screen;
    private World world;
    private Player player;
    private Character character;
    private RectangleMapObject doorMapObject;
    private DoorObject door;

    @BeforeEach
    void setUp() {
        world = new World(new Vector2(0, -9.81f), true);
    
        character = new Character(
                "test",
                new CharacterAttributes(10f, 1f, 2, 1f, 1f),
                new Vector2(1, 1),
                world
        );
        player = new Player(character);
    
        screen = mock(GameScreen.class);
        when(screen.getWorld()).thenReturn(world);
    
        doorMapObject = new RectangleMapObject(32, 32, 16, 16); 
        door = new DoorObject(screen, doorMapObject, player);
    }


//    @Test
//    void testDoorDoesNotTriggerWhenPlayerIsFarAway() {
//    character.setPosition(new Vector2(10f, 10f));
//    door.checkPlayerAtMiddle();
//    assertFalse(door.isTriggered(), "Door should not trigger when player is far away");
//
//}
    @Test
    void testDoorTriggersWhenPlayerAtCorrectSpot() {
        float doorCenterX = (32 + 16) / 16f;
        float doorBottomY = 32 / 16f;
    
        Vector2 playerPos = new Vector2(doorCenterX, doorBottomY);
        character.setPosition(playerPos); 
    
        door.checkPlayerAtMiddle();
    
        assertTrue(door.isTriggered(), "Door should trigger when player stands in middle");
    }

    @Test
    void testDoorOnlyTriggersOnce() {
    Vector2 pos = new Vector2((32 + 16) / 16f, 32 / 16f);
    character.setPosition(pos);

    door.checkPlayerAtMiddle(); 
    assertTrue(door.isTriggered());

    character.setPosition(new Vector2(999f, 999f));
    door.checkPlayerAtMiddle(); 

    assertTrue(door.isTriggered(), "Door should remain triggered after first activation");
}

    @Test
    void testThrowsIfMapObjectNotRectangle() {
        var nonRectMapObject = mock(com.badlogic.gdx.maps.MapObject.class);

        assertThrows(IllegalArgumentException.class, () ->
            new DoorObject(screen, nonRectMapObject, player)
        );
}
    @Test
    void testDisposeDoesNotCrash() {
        assertDoesNotThrow(() -> door.dispose(), "dispose should not crash");
    }

}