package chess.pieces;

import static chess.Player.Black;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chess.GameState;

@RunWith(MockitoJUnitRunner.class)
public class BishopTest extends AbstractPieceTest {

    @Mock
    private GameState gameState;

    @Test
    public void testMoves() {
        checkPositions(gameState, "d4", new Bishop(Black),
                "a1", "b2", "c3", "e5", "f6", "g7", "h8", "a7", "b6", "c5", "e3", "f2", "g1");
    }

    @Test
    public void testMovesBlocked() {
        mockBoardWithPieces(gameState, Black, "c3", "c5", "e3", "e5");
        checkPositions(gameState, "d4", new Bishop(Black), "c3", "c5", "e3", "e5");
    }
}