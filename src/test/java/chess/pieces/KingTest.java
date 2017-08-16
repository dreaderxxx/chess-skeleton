package chess.pieces;

import static chess.Player.Black;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chess.GameState;

@RunWith(MockitoJUnitRunner.class)
public class KingTest extends AbstractPieceTest {

    @Mock
    private GameState gameState;

    @Test
    public void testMoves() {
        checkPositions(gameState, "d4", new King(Black), "c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5");
    }

}