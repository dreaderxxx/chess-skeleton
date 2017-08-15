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
public class PawnTest {

    @Mock
    private GameState gameState;

    @Test
    public void test_GenerateMoves_SimpleWhite() {
        Pawn pawn = new Pawn(Player.White);
        List<Position> positions = pawn.generatePositions(new Position("e2"), gameState).collect(Collectors.toList());

        assertTrue(positions.size() == 2);
        assertTrue(positions.contains(new Position("e3")));
        assertTrue(positions.contains(new Position("e4")));
    }

    @Test
    public void test_GenerateMoves_SimpleBlack() {
        Pawn pawn = new Pawn(Player.Black);
        List<Position> positions = pawn.generatePositions(new Position("e2"), gameState).collect(Collectors.toList());

        assertTrue(positions.size() == 1);
        assertTrue(positions.contains(new Position("e1")));
    }

    @Test
    public void test_GenerateMoves_Attack_SamePlayer() {
        Pawn pawn = new Pawn(Player.White);
        when(gameState.getPieceAt(new Position("d4"))).thenReturn(new Pawn(Player.White));
        when(gameState.getPieceAt(new Position("f4"))).thenReturn(new Pawn(Player.White));

        List<Position> positions = pawn.generatePositions(new Position("e3"), gameState).collect(Collectors.toList());

        assertTrue(positions.size() == 1);
        assertTrue(positions.contains(new Position("e4")));
    }

    @Test
    public void test_GenerateMoves_Attack() {
        Pawn pawn = new Pawn(Player.White);
        when(gameState.getPieceAt(new Position("d4"))).thenReturn(new Pawn(Player.Black));
        when(gameState.getPieceAt(new Position("f4"))).thenReturn(new Pawn(Player.Black));

        List<Position> positions = pawn.generatePositions(new Position("e3"), gameState).collect(Collectors.toList());

        assertTrue(positions.size() == 3);
        assertTrue(positions.contains(new Position("e4")));
        assertTrue(positions.contains(new Position("d4")));
        assertTrue(positions.contains(new Position("f4")));
    }

    @Test
    public void test_GenerateMoves_Occupied() {
        when(gameState.getPieceAt(new Position("e3"))).thenReturn(new Pawn(Player.Black));
        Pawn pawn = new Pawn(Player.White);
        List<Position> positions = pawn.generatePositions(new Position("e2"), gameState).collect(Collectors.toList());

        assertTrue(positions.size() == 0);
    }
}
