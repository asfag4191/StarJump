//package inf112.skeleton.model.character;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import inf112.skeleton.model.game_objects.Player;
//import org.junit.jupiter.api.*;
//
//public class PlayerTest {
//    private Character player;
//
//    /**
//	 * Setup method called before each of the test methods
//	 */
//	@BeforeEach
//	void setUpBeforeEach() {
//        player = new Player("star", 100, 10);
//	}
//
//    @Test
//    void playerSanityTest() {
//        Character player1 = new Player("player 1", 100, 5);
//
//        assertEquals("player 1", player1.getName());
//        assertEquals(100, player1.getMaxHp());
//        assertEquals(100, player1.getHp());
//        assertEquals(5, player1.getStrength());
//    }
//
//    @Test
//    void takeDamageTest() {
//        player.takeDamage(3);
//        assertEquals(97, player.getHp());
//
//        player.takeDamage(-10);
//        assertEquals(97, player.getHp());
//
//        player.takeDamage(97);
//        assertEquals(0, player.getHp());
//
//        player.takeDamage(5);
//        assertEquals(0, player.getHp());
//    }
//
//    @Test
//    void toStringTest() {
//        assertEquals("star", player.toString());
//    }
//}
