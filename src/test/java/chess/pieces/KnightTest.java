package chess.pieces;

import static chess.Player.Black;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chess.GameState;

@RunWith(MockitoJUnitRunner.class)
public class KnightTest extends AbstractPieceTest {

    @Mock
    private GameState gameState;

    @Test
    public void testMoves() {
        checkPositions(gameState, "d4", new Knight(Black), "c6", "e6", "f5", "f3", "c2", "e2", "b3", "b5");
    }
}
