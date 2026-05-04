import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.boardSetup();
    }

    @Test
    public void testWhiteGoesFirst() {
        assertTrue(board.isWhiteTurn);
    }
}
