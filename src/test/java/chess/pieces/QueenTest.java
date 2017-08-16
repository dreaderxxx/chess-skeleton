package chess.pieces;

import static chess.Player.Black;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chess.GameState;

@RunWith(MockitoJUnitRunner.class)
public class QueenTest extends AbstractPieceTest {

    @Mock
    private GameState gameState;

    @Test
    public void testMoves() {
        checkPositions(gameState, "d4", new Queen(Black),
                "a1", "b2", "c3", "e5", "f6", "g7", "h8", "a7", "b6", "c5", "e3", "f2", "g1", "a4", "b4", "c4", "e4", "f4", "g4", "h4", "d1",
                "d2", "d3", "d5", "d6", "d7", "d8");
    }

    @Test
    public void testMovesBlocked() {
        mockBoardWithPieces(gameState, Black, "c3", "c4", "c5", "e3", "e4", "e5", "d3", "d5");
        checkPositions(gameState, "d4", new Queen(Black), "c3", "c4", "c5", "e3", "e4", "e5", "d3", "d5");
    }

}