package chess.pieces;

import static chess.Player.White;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chess.GameState;

@RunWith(MockitoJUnitRunner.class)
public class RookTest extends AbstractPieceTest {

    @Mock
    private GameState gameState;

    @Test
    public void testMoves() {
        checkPositions(gameState, "d4", new Rook(White),
                "a4", "b4", "c4", "e4", "f4", "g4", "h4", "d1", "d2", "d3", "d5", "d6", "d7", "d8");
    }

    @Test
    public void testMovesBlocked() {
        mockBoardWithPieces(gameState, White, "c4", "e4", "d5", "d3");
        checkPositions(gameState, "d4", new Rook(White), "c4", "e4", "d5", "d3");
    }

}
