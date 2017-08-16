package chess.pieces;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import chess.GameState;
import chess.Player;
import chess.Position;

public abstract class AbstractPieceTest {

    protected void mockBoardWithPieces(GameState gameState, Player player, String... positions) {
        for (String position : positions) {
            when(gameState.getPieceAt(new Position(position))).thenReturn(new Pawn(player));
        }
    }

    protected void checkPositions(GameState gameState, String start, Piece piece, String... expectedPositions) {
        List<Position> actualPositions = piece.generatePositions(new Position(start), gameState).collect(Collectors.toList());
        assertTrue("Wrong total number of positions", actualPositions.size() == expectedPositions.length);
        for (String expected : expectedPositions) {
            assertTrue("Expected position " + expected + " not generated",
                    actualPositions.contains(new Position(expected)));
        }
    }

}