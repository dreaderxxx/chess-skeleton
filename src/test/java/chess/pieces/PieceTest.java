package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PieceTest {

    @Mock
    private Position testPosition;
    @Mock
    private Position testPosition2;
    @Mock
    private GameState gameState;

    @Test
    public void testFilterInvalidPositions() {
        assertTrue(new TestPiece(null).getPossibleMoves(null, gameState).count() == 0);
    }

    @Test
    public void testFilterFriendlyPiecePositions() {
        when(gameState.getPieceAt(testPosition)).thenReturn(new Pawn(Player.White));
        when(testPosition.isValid()).thenReturn(true);
        when(gameState.getPieceAt(testPosition2)).thenReturn(new Pawn(Player.Black));
        when(testPosition2.isValid()).thenReturn(true);

        assertTrue(new TestPiece(Player.White).getPossibleMoves(null, gameState).count() == 1);
    }

    private class TestPiece extends Piece {
        TestPiece(Player owner) {
            super(owner);
        }

        @Override
        protected Stream<Position> generatePositions(Position start, GameState gameState) {
            return Stream.of(
                    testPosition,
                    testPosition2,
                    new Position("j2"),
                    new Position("b9"),
                    new Position("_2"),
                    new Position('h', -1)
            );
        }

        @Override
        protected char getIdentifyingCharacter() {
            return 0;
        }
    }
}
