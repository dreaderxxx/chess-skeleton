package chess.pieces;

import static chess.Player.Black;
import static chess.Player.White;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chess.GameState;

@RunWith(MockitoJUnitRunner.class)
public class PawnTest extends AbstractPieceTest {

    @Mock
    private GameState gameState;

    @Test
    public void test_GenerateMoves_SimpleWhite() {
        checkPositions(gameState, "e2", new Pawn(White), "e3", "e4");
    }

    @Test
    public void test_GenerateMoves_SimpleBlack() {
        checkPositions(gameState, "e2", new Pawn(Black), "e1");
    }

    @Test
    public void test_GenerateMoves_Attack_SamePlayer() {
        mockBoardWithPieces(gameState, White, "d4", "f4");
        checkPositions(gameState, "e3", new Pawn(White), "e4");
    }

    @Test
    public void test_GenerateMoves_Attack() {
        mockBoardWithPieces(gameState, Black, "d4", "f4");
        checkPositions(gameState, "e3", new Pawn(White), "e4", "d4", "f4");
    }

    @Test
    public void test_GenerateMoves_Occupied() {
        mockBoardWithPieces(gameState, Black, "e3");
        checkPositions(gameState, "e2", new Pawn(White));
    }
}
