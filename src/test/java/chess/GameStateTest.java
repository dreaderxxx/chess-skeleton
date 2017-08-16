package chess;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());

        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }

    @Test
    public void testValidMove() {
        state.reset();
        Position from = new Position("e2");
        Position to = new Position("e4");
        Player player = state.getCurrentPlayer();
        Piece piece = state.getPieceAt(from);
        assertNotNull(piece);
        state.move(from, to);
        assertNull("Piece must be moved", state.getPieceAt(from));
        assertEquals("Piece must be moved", piece, state.getPieceAt(to));
        assertTrue(player != state.getCurrentPlayer());
    }

    @Test
    public void testMoveInvalidMove() {
        state.reset();
        Position from = new Position("e2");
        Position to = new Position("e5");
        Player player = state.getCurrentPlayer();
        Piece piece = state.getPieceAt(from);
        assertNotNull(piece);
        state.move(from, to);
        assertNotNull("Piece must not be moved", state.getPieceAt(from));
        assertNull("Piece must not be moved", state.getPieceAt(to));
        assertEquals("Player must not be changed", player, state.getCurrentPlayer());
    }

    @Test
    public void testGetMoves_Count() {
        state.reset();
        List<Move> moves = state.moves().collect(Collectors.toList());
        assertEquals("Wrong number of moves", 20, moves.size());
    }

    @Test
    public void testCheckmate() {
        state.placePiece(new King(Player.White), new Position("a1"));
        state.placePiece(new Rook(Player.Black), new Position("a8"));
        state.placePiece(new Queen(Player.Black), new Position("b8"));
        assertTrue("Must be checkmate", state.isCheckMate());
        assertFalse("Must not be draw", state.isDraw());
    }

    @Test
    public void testDraw() {
        state.placePiece(new King(Player.White), new Position("a1"));
        state.placePiece(new Rook(Player.Black), new Position("b8"));
        state.placePiece(new Queen(Player.Black), new Position("h2"));
        assertFalse("Must not be checkmate", state.isCheckMate());
        assertTrue("Must be draw", state.isDraw());
    }

    @Test
    public void testInProgress() {
        state.placePiece(new King(Player.White), new Position("a1"));
        state.placePiece(new Rook(Player.White), new Position("h2"));
        state.placePiece(new Rook(Player.Black), new Position("a8"));
        state.placePiece(new Queen(Player.Black), new Position("b8"));
        assertFalse("Must not be checkmate", state.isCheckMate());
        assertFalse("Must not be draw", state.isDraw());
    }
}
