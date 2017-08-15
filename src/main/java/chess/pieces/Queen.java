package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * The Queen
 */
public class Queen extends Piece {
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    public Stream<Position> generatePositions(Position start, GameState gameState) {
        return Stream.of(
                new Rook(getOwner()).generatePositions(start, gameState),
                new Bishop(getOwner()).generatePositions(start, gameState)).flatMap(Function.identity());
    }
}
