package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RookTest {

    @Mock
    private GameState gameState;

    @Test
    public void testRookPositions() {
        Rook rook = new Rook(Player.White);
        List<Position> positions = rook.generatePositions(new Position("d4"), gameState).collect(Collectors.toList());
        assertTrue(positions.size() == 14);
        assertTrue(positions.contains(new Position("a4")));
        assertTrue(positions.contains(new Position("b4")));
        assertTrue(positions.contains(new Position("c4")));
        assertTrue(positions.contains(new Position("e4")));
        assertTrue(positions.contains(new Position("f4")));
        assertTrue(positions.contains(new Position("g4")));
        assertTrue(positions.contains(new Position("h4")));
        assertTrue(positions.contains(new Position("d1")));
        assertTrue(positions.contains(new Position("d2")));
        assertTrue(positions.contains(new Position("d3")));
        assertTrue(positions.contains(new Position("d5")));
        assertTrue(positions.contains(new Position("d6")));
        assertTrue(positions.contains(new Position("d7")));
        assertTrue(positions.contains(new Position("d8")));
    }

    @Test
    public void testRookPositions_CheckEndPositions() {
        when(gameState.getPieceAt(new Position("c4"))).thenReturn(new Rook(Player.White));
        when(gameState.getPieceAt(new Position("e4"))).thenReturn(new Rook(Player.White));
        when(gameState.getPieceAt(new Position("d5"))).thenReturn(new Rook(Player.Black));
        when(gameState.getPieceAt(new Position("d3"))).thenReturn(new Rook(Player.White));

        Rook rook = new Rook(Player.White);
        List<Position> positions = rook.generatePositions(new Position("d4"), gameState).collect(Collectors.toList());
        assertTrue(positions.size() == 4);
        assertTrue(positions.contains(new Position("c4")));
        assertTrue(positions.contains(new Position("e4")));
        assertTrue(positions.contains(new Position("d5")));
        assertTrue(positions.contains(new Position("d3")));
    }

}
